package com.sckill.sckill.controller;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Item item = itemService.findById(id);
        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);

        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Item> itens = itemService.toListUser();
        List<ItemDTO> userDTOList = itens.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody final ItemDTO dto) {
        Item item = itemService.save(dto);
        ItemDTO itemDTO= modelMapper.map(item, ItemDTO.class);

        return ResponseEntity.ok(itemDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        itemService.delete(id);

        return ResponseEntity.ok().build();
    }
}
