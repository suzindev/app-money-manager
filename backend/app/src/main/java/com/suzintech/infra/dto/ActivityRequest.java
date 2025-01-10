package com.suzintech.infra.dto;

import java.time.Instant;

public record ActivityRequest(
        String description,
        Instant date,
        float value,
        String type
) {
}
