package com.suzintech.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "E-mail should not be blank")
        @Email(message = "Email is not valid")
        String email,

        @NotBlank(message = "Password should not be blank")
        String password
) {
}
