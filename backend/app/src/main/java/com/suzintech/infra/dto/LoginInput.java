package com.suzintech.infra.dto;

public record LoginInput(
        String email,
        String password
) {
}
