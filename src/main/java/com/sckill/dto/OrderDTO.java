package com.sckill.dto;

import com.sckill.entities.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

    Long id;
    UserDTO user;
    LocalDateTime creationDate;
    OrderStatus status;
}
