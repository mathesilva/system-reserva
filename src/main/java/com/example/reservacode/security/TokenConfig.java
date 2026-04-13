package com.example.reservacode.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.reservacode.entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TokenConfig {

    private String secret = "secret";

    public String GerarToken(Usuario user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withIssuer("security")
                .withClaim( "userId", user.getId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTuserData> validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTuserData.builder().id(decode.getClaim("userID").asLong()).email(decode.getSubject()).build());
        } catch (JWTVerificationException e){
            return Optional.empty();
        }
    }
}
