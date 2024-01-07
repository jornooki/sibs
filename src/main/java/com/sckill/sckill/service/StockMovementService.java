package com.sckill.sckill.service;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.dto.StockMovementDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.entities.Order;
import com.sckill.sckill.entities.StockMovement;
import com.sckill.sckill.entities.enums.OrderSituation;
import com.sckill.sckill.exception.BussinesException;
import com.sckill.sckill.exception.InvalidIdException;
import com.sckill.sckill.repository.ItemRepository;
import com.sckill.sckill.repository.OrderRepository;
import com.sckill.sckill.repository.StockMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public StockMovement findById(Long id) {
        return stockMovementRepository.findById(id).orElseThrow(() -> new InvalidIdException("ID invalid: " + id));
    }

    public List<StockMovement> toListStockMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement save(StockMovementDTO dto) {

        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow(() -> new BussinesException("Order not found"));
        Item item = itemRepository.findById(dto.getItemId()).orElseThrow(() -> new BussinesException("Item not found"));

        if (order.getSituation().equals(OrderSituation.CLOSED)) {
            throw new BussinesException("Order is already closed");
        }

        StockMovement stockMovement = loadStockMovement(dto);
        stockMovement.setOrder(order);
        stockMovement.setItem(item);

        return stockMovementRepository.saveAndFlush(stockMovement);
    }

    private StockMovement loadStockMovement(StockMovementDTO dto) {
        StockMovement.StockMovementBuilder<?, ?> builder;
        if (dto.getId() == null) {
            builder = StockMovement.builder()
                    .creationDate(LocalDateTime.now())
                    .quantity(dto.getQuantity());
        } else {
            StockMovement stockMovement = stockMovementRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Stock movement not found with ID " + dto.getId()));
            builder = stockMovement.toBuilder();
        }

        return builder.creationDate(LocalDateTime.now()).quantity(dto.getQuantity()).build();
    }

    public void delete(Long id) {
        stockMovementRepository.deleteById(id);
    }

    public List<StockMovement> toListStockMovementsByOrder(Long orderId) {
        return stockMovementRepository.findByOrder_Id(orderId);

    }
}
