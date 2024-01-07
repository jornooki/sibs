package com.sckill.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDTO {

    Long id;
    
    @NotNull(message = "Name is mandatory")
    String name;

    @NotNull(message = "Quantity is mandatory")
    Long quantity;
}
