package com.example.reservacode.repository;

import com.example.reservacode.entity.Reserva;
import com.example.reservacode.entity.Sala;
import com.example.reservacode.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsBySalaAndHoraInicioAndHoraFinal(Sala sala, LocalDateTime horaInicio, LocalDateTime horaFinal);

    boolean existsBySala(Sala sala);

    boolean existsByUsuario(Usuario usuario);
}
