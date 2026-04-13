package com.example.reservacode.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.antlr.v4.runtime.misc.NotNull;

public record LoginRequest(@NotEmpty(message = "email é obrigatório") String email,
                           @NotEmpty(message = "senha é obrigatória") String password) {
}
