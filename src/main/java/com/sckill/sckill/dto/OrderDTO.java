package com.sckill.sckill.dto;

import com.sckill.sckill.entities.enums.OrderSituation;
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
    Long userId;
    LocalDateTime creationDate;
    OrderSituation situation;
}
