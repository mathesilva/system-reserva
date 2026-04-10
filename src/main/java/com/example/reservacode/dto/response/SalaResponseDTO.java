package com.example.reservacode.dto.response;

import com.example.reservacode.entity.Sala;

public record SalaResponseDTO (Long id,
                               String nome,
                               int capacidade,
                               boolean ativa){
    public SalaResponseDTO (Sala sala){
        this(sala.getId(), sala.getNome(), sala.getCapacidade(), sala.isAtiva());
    }
}
