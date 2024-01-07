package com.sckill.sckill.service;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.dto.StockMovementDTO;
import com.sckill.sckill.entities.Order;
import com.sckill.sckill.entities.StockMovement;
import com.sckill.sckill.exception.InvalidIdException;
import com.sckill.sckill.repository.OrderRepository;
import com.sckill.sckill.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final OrderRepository orderRepository;
    public StockMovement findById(Long id) {
        return stockMovementRepository.findById(id).orElseThrow(() -> new InvalidIdException("ID invalid: " + id));
    }

    public List<StockMovement> toListStockMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement save(StockMovementDTO dto) {
        return null;
    }

    public void delete(Long id) {
        stockMovementRepository.deleteById(id);
    }

    public List<StockMovement> toListStockMovementsByOrder(Long orderId) {
        return stockMovementRepository.findByOrder_Id(orderId);

    }
}
