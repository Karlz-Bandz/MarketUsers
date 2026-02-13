package com.izzisoft.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarketUserRegisterRequest(
        @Email(message = "Email should be valid!")
        String email,
        @NotBlank(message = "Username cannot be blank!")
        String username,
        @NotBlank(message = "Username cannot be blank!")
        @Size(min = 4, message = "Password must be at least 4 characters long!")
        String password
) {
}
