package com.suzintech.infra.dto;

import java.time.Instant;

public record InsertActivityOutput(
        String id,
        Instant date,
        String description,
        float value,
        String type
) {
}
