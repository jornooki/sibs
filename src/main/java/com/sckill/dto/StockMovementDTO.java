package com.sckill.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMovementDTO {

    Long id;
    LocalDateTime creationDate;
    int quantity;
    Long orderId;
    Long itemId;
}
