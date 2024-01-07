package com.sckill.controller;

import com.sckill.dto.StockMovementDTO;
import com.sckill.entities.StockMovement;
import com.sckill.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;
    private final ModelMapper modelMapper;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        StockMovement stockMovement = stockMovementService.findById(id);
        StockMovementDTO stockMovementDTO = modelMapper.map(stockMovement, StockMovementDTO.class);

        return ResponseEntity.ok(stockMovementDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<StockMovement> stockMovements = stockMovementService.findAll();
        List<StockMovementDTO> userDTOList = stockMovements.stream()
                .map(stockMovement -> modelMapper.map(stockMovement, StockMovementDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/findByOrder/{orderId}")
    public ResponseEntity<?> findByOrder(@PathVariable("orderId") Long orderId) {
        List<StockMovement> stockMovements = stockMovementService.toListStockMovementsByOrder(orderId);
        List<StockMovementDTO> userDTOList = stockMovements.stream()
                .map(stockMovement -> modelMapper.map(stockMovement, StockMovementDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody final StockMovementDTO dto) {
        StockMovement stockMovement = stockMovementService.save(dto);
        StockMovementDTO StockMovementDTO = modelMapper.map(stockMovement, StockMovementDTO.class);

        return ResponseEntity.ok(StockMovementDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        stockMovementService.delete(id);

        return ResponseEntity.ok().build();
    }
}
