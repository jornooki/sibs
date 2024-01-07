package com.sckill.service;

import com.sckill.dto.UserDTO;
import com.sckill.entities.User;
import com.sckill.exception.InvalidIdException;
import com.sckill.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new InvalidIdException("ID invalid: " + id));

    }

    public User save(UserDTO dto) {
        User user = loadUser(dto);
        return userRepository.save(user);
    }

    private User loadUser(UserDTO dto) {

        User.UserBuilder builder;
        if (dto.getId() == null) {
            builder = User.builder();
        } else {
            User user = userRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + dto.getId()));
            builder = user.toBuilder();
        }

        return builder
                .name(dto.getName())
                .email(dto.getEmail()).build();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
