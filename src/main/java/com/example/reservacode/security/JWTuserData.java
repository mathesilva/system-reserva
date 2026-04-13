package com.example.reservacode.security;

import lombok.Builder;


@Builder
public record JWTuserData (Long id, String email){
}
