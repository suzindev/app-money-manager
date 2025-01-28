package com.suzintech.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.suzintech.domain.User;
import com.suzintech.exception.AuthException;
import com.suzintech.exception.LoginException;
import com.suzintech.infra.dto.LoginInput;
import com.suzintech.infra.dto.LoginOutput;
import com.suzintech.utils.InstantUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    final User uniqueUser = User.with("tech@email.com", "tech");

    private final String TOKEN_SECRET = "0123456789";

    private final String TOKEN_ISSUER = "money.manager";

    public LoginOutput login(final LoginInput input) {
        final var user = User.with(input.email(), input.password());

        if (!uniqueUser.getEmail().equals(user.getEmail()) || !uniqueUser.getPassword().equals(user.getPassword())) {
            throw new LoginException("User or password not found");
        }

        final var token = this.createToken(user);

        return new LoginOutput(token);
    }

    public String validateToken(final String token) {
        try {
            final var algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var verifier = JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build();
            final var decodedToken = verifier.verify(token);

            return decodedToken.getSubject();
        } catch (Exception e) {
            return "";
        }
    }

    private String createToken(final User user) {
        try {
            final var algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            return JWT.create()
                    .withIssuer(TOKEN_ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 4))
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username.equals(this.uniqueUser.getUsername())) {
            return this.uniqueUser;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
