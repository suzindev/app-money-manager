package com.suzintech.exception;

import java.time.Instant;

public record ExceptionResponseBody(
        Instant timestamp,
        Integer status,
        String error,
        String path
) {
}
