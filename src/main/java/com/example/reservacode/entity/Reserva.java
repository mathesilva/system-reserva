package com.example.reservacode.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reserva")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private  Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;


    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime horaInicio;
    @Column(name = "data_final", nullable = false)
    private LocalDateTime horaFinal;


    public Reserva(){}

}
