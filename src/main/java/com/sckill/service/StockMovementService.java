package com.sckill.service;

import com.sckill.entities.Item;
import com.sckill.entities.Order;
import com.sckill.entities.StockMovement;
import com.sckill.entities.enums.OrderStatus;
import com.sckill.exception.BusinessException;
import com.sckill.exception.InvalidIdException;
import com.sckill.dto.StockMovementDTO;
import com.sckill.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final OrderService orderService;
    private final ItemService itemService;

    public StockMovement findById(Long id) {
        return stockMovementRepository.findById(id).orElseThrow(() -> new InvalidIdException("ID Invalid: " + id));
    }

    public List<StockMovement> findAll() {
        return stockMovementRepository.findAll();
    }

    public StockMovement save(StockMovementDTO dto) {

        Order order = orderService.findById(dto.getOrderId());
        Item item = itemService.findById(dto.getItemId());

        if (order.getStatus().equals(OrderStatus.CLOSED)) {
            log.error("Order" + order.getId() + " is already closed");
            throw new BusinessException("Order is already closed");
        }

        updateStock(dto, item);

        StockMovement stockMovement = loadStockMovement(dto);
        stockMovement.setOrder(order);
        stockMovement.setItem(item);

        return stockMovementRepository.save(stockMovement);
    }

    private void updateStock(StockMovementDTO dto, Item item) {
        if (dto.getQuantity() > item.getQuantity()) {
            throw new BusinessException("insufficient quantity in stock");
        } else {
            item.setQuantity(item.getQuantity() - dto.getQuantity());
            itemService.save(item);
        }
    }

    private StockMovement loadStockMovement(StockMovementDTO dto) {
        StockMovement.StockMovementBuilder builder;
        if (dto.getId() == null) {
            builder = StockMovement.builder();
        } else {
            StockMovement stockMovement = findById(dto.getId());
            builder = stockMovement.toBuilder();
        }

        return builder.creationDate(LocalDateTime.now()).quantity(dto.getQuantity()).build();
    }

    public void delete(Long id) {
        stockMovementRepository.deleteById(id);
    }

    public List<StockMovement> toListStockMovementsByOrder(Long orderId) {
        return stockMovementRepository.findByOrderId(orderId);

    }
}
