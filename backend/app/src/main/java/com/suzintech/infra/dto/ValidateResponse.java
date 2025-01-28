package com.suzintech.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidateResponse(
        @JsonProperty("is_valid")
        boolean isValid
) {
}
