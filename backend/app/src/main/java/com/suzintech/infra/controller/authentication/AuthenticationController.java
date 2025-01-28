package com.suzintech.infra.controller.authentication;

import com.suzintech.infra.dto.LoginRequest;
import com.suzintech.infra.dto.LoginResponse;
import com.suzintech.infra.dto.ValidateRequest;
import com.suzintech.infra.dto.ValidateResponse;
import com.suzintech.infra.mapper.LoginRequestToLoginInputMapper;
import com.suzintech.infra.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(final AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid final LoginRequest request) {
        final var input = LoginRequestToLoginInputMapper.build().apply(request);
        final var output = this.authService.login(input);
        final var response = new LoginResponse(output.token());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponse> validate(@RequestBody @Valid final ValidateRequest request) {
        final var output = this.authService.validateToken(request.token());
        var isValid = !output.isBlank();

        return ResponseEntity.ok().body(new ValidateResponse(isValid));
    }
}
