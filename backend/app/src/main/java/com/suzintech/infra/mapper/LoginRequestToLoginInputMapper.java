package com.suzintech.infra.mapper;

import com.suzintech.infra.dto.LoginInput;
import com.suzintech.infra.dto.LoginRequest;

import java.util.function.Function;

public class LoginRequestToLoginInputMapper implements Function<LoginRequest, LoginInput> {

    public static LoginRequestToLoginInputMapper build() {
        return new LoginRequestToLoginInputMapper();
    }

    @Override
    public LoginInput apply(final LoginRequest request) {
        return new LoginInput(request.email(), request.password());
    }
}
