package com.suzintech.exception;

import com.suzintech.utils.InstantUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ActivityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    protected ResponseEntity<ExceptionResponseBody> handlerDomainException(final DomainException exception, final HttpServletRequest request) {
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {PersistenceException.class})
    protected ResponseEntity<ExceptionResponseBody> handlerPersistenceException(final PersistenceException exception, final HttpServletRequest request) {
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<ExceptionResponseBody> handlerServiceException(final ServiceException exception, final HttpServletRequest request) {
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ExceptionResponseBody> handlerException(final Exception exception, final HttpServletRequest request) {
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.internalServerError().body(body);
    }
}
