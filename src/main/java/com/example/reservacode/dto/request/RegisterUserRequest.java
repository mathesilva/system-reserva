package com.example.reservacode.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(@NotEmpty(message = "nome é obrigatorio") String nome,
                                  @NotEmpty(message = "email é obrigatorio") String email,
                                  @NotEmpty(message = "senha é obrigatoria")String password) {
}
