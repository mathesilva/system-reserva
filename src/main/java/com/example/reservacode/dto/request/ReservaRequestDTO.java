package com.example.reservacode.dto.request;

import com.example.reservacode.entity.Reserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReservaRequestDTO (
        Long id,
        String sala,
        String usuario,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime horaInicio,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime horaFinal
)   {
    public ReservaRequestDTO(Reserva reserva) {
        this(reserva.getId(), reserva.getSala().getNome(),reserva.getUsuario().getNome(),reserva.getHoraInicio(),reserva.getHoraFinal());
    }
}
