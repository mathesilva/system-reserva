package com.example.reservacode.dto.request;

import com.example.reservacode.entity.Usuario;

public record UsuarioRequestDTO(
        Long id,
        String nome,
        String email){
    public UsuarioRequestDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
