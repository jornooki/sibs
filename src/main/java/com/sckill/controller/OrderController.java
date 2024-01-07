package com.sckill.controller;

import com.sckill.dto.OrderDTO;
import com.sckill.entities.Order;
import com.sckill.dto.IdDto;
import com.sckill.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        OrderDTO itemDTO = modelMapper.map(order, OrderDTO.class);

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> userDTOList = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody final OrderDTO dto) {
        Order order = orderService.save(dto);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping("/close")
    public ResponseEntity<?> close(@RequestBody final IdDto dto) {
        Order order = orderService.complete(dto);
        OrderDTO orderDTO= modelMapper.map(order, OrderDTO.class);

        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        orderService.delete(id);

        return ResponseEntity.ok().build();
    }
}
