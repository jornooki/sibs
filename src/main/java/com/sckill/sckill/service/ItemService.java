package com.sckill.sckill.service;

import com.sckill.sckill.controller.ItemController;
import com.sckill.sckill.dto.UserDTO;
import com.sckill.sckill.entities.Item;
import com.sckill.sckill.entities.User;
import com.sckill.sckill.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private ItemRepository itemRepository;

    public Item findById(Long id) {
        return null;
    }

    public List<Item> toListUser() {
        return itemRepository.findAll();
    }

    public Item save(UserDTO dto) {
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
