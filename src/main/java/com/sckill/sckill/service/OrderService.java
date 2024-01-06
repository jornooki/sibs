package com.sckill.sckill.service;

import com.sckill.sckill.dto.OrderDTO;
import com.sckill.sckill.entities.Order;
import com.sckill.sckill.entities.User;
import com.sckill.sckill.entities.enums.OrderSituation;
import com.sckill.sckill.exception.BussinesException;
import com.sckill.sckill.repository.OrderRepository;
import com.sckill.sckill.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<Order> toListOrder() {

        return orderRepository.findAll();
    }

    public Order save(OrderDTO dto) {

        Order order = loadOrder(dto);
        User user = findUserById(dto.getUserId());

        order.setUser(user);
        return orderRepository.saveAndFlush(order);

    }

    private Order loadOrder(OrderDTO dto) {

            Order.OrderBuilder<?, ?> builder;
            if (dto.getId() == null) {
                builder = Order.builder();
            } else {
                Order order = orderRepository.findById(dto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + dto.getId()));
                builder = order.toBuilder();
            }

            return builder.situation(OrderSituation.OPEN).creationDate(LocalDateTime.now()).build();
        }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BussinesException("User not found with ID" + id));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}