package com.suzintech.infra.dto;

import java.time.Instant;

public record InsertActivityInput(
        Instant date,
        String description,
        float value,
        String type
) {
}
