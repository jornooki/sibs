package com.sckill.sckill.service;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.exception.InvalidIdException;
import com.sckill.sckill.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new InvalidIdException("invalid ID: " + id));
    }

    public List<Item> toListUser() {
        return itemRepository.findAll();
    }

    public Item save(ItemDTO dto) {
        Item user = loadItem(dto);
        return itemRepository.saveAndFlush(user);
    }

    private Item loadItem(ItemDTO dto) {
        Item.ItemBuilder<?, ?> builder;
        if (dto.getId() == null) {
            builder = Item.builder()
                    .name(dto.getName());
        } else {
            Item item = itemRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found with ID " + dto.getId()));
            builder = item.toBuilder();
        }

        return builder.name(dto.getName()).build();
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
