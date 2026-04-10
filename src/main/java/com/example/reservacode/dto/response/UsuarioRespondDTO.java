package com.example.reservacode.dto.response;

import com.example.reservacode.entity.Usuario;

public record UsuarioRespondDTO(
        Long id,
        String nome,
        String email) {
    public UsuarioRespondDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
