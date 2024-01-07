package com.sckill.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;

    @Email(message = "Invalid E-mail")
    @NotBlank(message = "Email is mandatory")
    String email;

    @NotBlank(message = "Name is mandatory")
    String name;
}
