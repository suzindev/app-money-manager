package com.suzintech.infra.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRequest(
        @NotBlank(message = "token should not be blank")
        String token
) {
}
