package com.sckill.sckill.service;

import com.sckill.sckill.dto.ItemDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.exception.BusinessException;
import com.sckill.sckill.exception.InvalidIdException;
import com.sckill.sckill.repository.ItemRepository;
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

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(ItemDTO dto) {
        if (dto.getQuantity() < 0) {
            throw new BusinessException("Invalid value for value " + dto.getQuantity());
        }
        Item user = loadItem(dto);
        return itemRepository.saveAndFlush(user);
    }

    private Item loadItem(ItemDTO dto) {
        Item.ItemBuilder builder;
        if (dto.getId() == null) {
            builder = Item.builder();
        } else {
            Item item = findById(dto.getId());
            builder = item.toBuilder();
        }

        return builder.name(dto.getName()).quantity(dto.getQuantity()).build();
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }
}
