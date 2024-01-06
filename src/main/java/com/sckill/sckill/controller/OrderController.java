package com.sckill.sckill.controller;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.dto.OrderDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.entities.Order;
import com.sckill.sckill.service.OrderService;
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

//    @GetMapping("/findById/{id}")
//    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
//        Order order = orderService.findById(id);
//        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//
//        return ResponseEntity.ok(itemDTO);
//    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Order> ordens = orderService.toListOrder();
        List<OrderDTO> userDTOList = ordens.stream()
                .map(item -> modelMapper.map(item, OrderDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody final OrderDTO dto) {
        Order order = orderService.save(dto);
        OrderDTO orderDTO= modelMapper.map(order, OrderDTO.class);

        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        orderService.delete(id);

        return ResponseEntity.ok().build();
    }
}
