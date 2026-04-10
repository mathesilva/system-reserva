package com.example.reservacode.dto.request;
import com.example.reservacode.entity.Sala;

public record SalaRequestDTO(
        Long id,
        String nome,
        int capacidade) {
    public SalaRequestDTO (Sala sala){
        this(sala.getId(), sala.getNome(), sala.getCapacidade());
    }
}
