package com.suzintech.infra.dto;

import java.time.Instant;

public record ActivityResponse(
        String id,
        Instant date,
        String description,
        float value,
        String type
) {
}
