package com.sckill.service;

import com.sckill.dto.OrderDTO;
import com.sckill.entities.Order;
import com.sckill.entities.User;
import com.sckill.entities.enums.OrderStatus;
import com.sckill.exception.BusinessException;
import com.sckill.repository.OrderRepository;
import com.sckill.dto.IdDto;
import com.sckill.repository.UserRepository;
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
    private final EmailService emailService;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with ID " + id));
    }
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(OrderDTO dto) {

        Order order = loadOrder(dto);
        User user = findUserById(dto.getUserId());

        order.setUser(user);
        return orderRepository.saveAndFlush(order);

    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order complete(IdDto dto) {
        Order order = findById(dto.getId());
        order.setStatus(OrderStatus.CLOSED);
        emailService.sendEmail(order.getUser().getEmail(), order.getId().toString());
        return orderRepository.saveAndFlush(order);
    }

    private Order loadOrder(OrderDTO dto) {

            Order.OrderBuilder builder;
            if (dto.getId() == null) {
                builder = Order.builder();
            } else {
                Order order = findById(dto.getId());
                builder = order.toBuilder();
            }

            return builder.status(OrderStatus.OPEN).creationDate(LocalDateTime.now()).build();
        }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found with ID" + id));
    }

}
