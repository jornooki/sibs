package com.sckill.sckill.service;

import com.sckill.sckill.dto.UserDTO;
import com.sckill.sckill.entities.User;
import com.sckill.sckill.exception.IdInvalidoException;
import com.sckill.sckill.repository.UserRepository;
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
        return userRepository.findById(id).orElseThrow(() -> new IdInvalidoException("ID inválido: " + id));

    }

    public User save(UserDTO dto) {
        User user = loadUser(dto);
        return userRepository.saveAndFlush(user);
    }

    private User loadUser(UserDTO dto) {

        User.UserBuilder<?, ?> builder;
        if (dto.getId() == null) {
            builder = User.builder()
                    .name(dto.getName())
                    .email(dto.getEmail());
        } else {
            User user = userRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + dto.getId()));
            builder = user.toBuilder();
        }

        return builder
                .name(dto.getName())
                .email(dto.getEmail()).build();
    }

    public List<User> toListUser() {
        return userRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        try {
            userRepository.deleteById(id);
        } catch (Exception e ) {
            throw new Exception("User not found");
        }
    }
}
