package com.example.reservacode.service;

import com.example.reservacode.dto.request.ReservaRequestDTO;
import com.example.reservacode.dto.response.ReservaRespondDTO;
import com.example.reservacode.entity.Reserva;
import com.example.reservacode.entity.Sala;
import com.example.reservacode.entity.Usuario;
import com.example.reservacode.exceptions.ErrorException;
import com.example.reservacode.exceptions.NaoEncontrado;
import com.example.reservacode.exceptions.SalaOcupada;
import com.example.reservacode.repository.ReservaRepository;
import com.example.reservacode.repository.SalaRepository;
import com.example.reservacode.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReservaService {

   private final ReservaRepository reservaRepository;
   private final UserRepository userRepository;
   private final SalaRepository salaRepository;

    public ReservaService(ReservaRepository reservaRepository, SalaRepository salaRepository, UserRepository userRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.userRepository = userRepository;
    }

    public ReservaRespondDTO criarReserva(ReservaRequestDTO request){

        Sala sala = salaRepository.findByNome(request.sala())
                .orElseThrow(()-> new NaoEncontrado("Sala nao encontrada"));

        if (!sala.isAtiva()){
            throw new SalaOcupada("Esta sala está em manutenção/desativada e não pode receber reservas.");
        }
        if (request.horaInicio().isBefore(LocalDateTime.now())){
            throw new ErrorException("Voce nao pode reservar um horario no passado");
        }

        if (reservaRepository.existsBySalaAndHoraInicioAndHoraFinal(sala,request.horaInicio(), request.horaFinal())){
            throw new SalaOcupada("A sala já esta reservada para este horario");
        }

        Usuario usuario = userRepository.findByNome(request.usuario().trim())
                .orElseThrow(() -> new NaoEncontrado("Usuario nao encontrado"));

        Reserva reservaEntity = new Reserva();
        reservaEntity.setSala(sala);
        reservaEntity.setUsuario(usuario);
        reservaEntity.setHoraInicio(request.horaInicio());
        reservaEntity.setHoraFinal(request.horaFinal());

        Reserva reservaSalva = reservaRepository.save(reservaEntity);
        return new ReservaRespondDTO(reservaSalva);
    }

    public List<ReservaRespondDTO> listarReservas(){
        List<Reserva> listaReservas = reservaRepository.findAll();
        return listaReservas.stream().map(ReservaRespondDTO::new).toList();
    }

    public ReservaRespondDTO buscarId(Long id){
        Reserva reservaId = reservaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontrado("Reserva nao encontrada"));
        return new ReservaRespondDTO(reservaId);
    }

    public Reserva atualizarReserva(ReservaRequestDTO dto,Long id){

        Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new NaoEncontrado("Reserva nao encontrada"));
        Sala sala = salaRepository.findByNome(dto.sala()).orElseThrow(() -> new NaoEncontrado("Sala nao encontrada"));
        Usuario usuario = userRepository.findByNome(dto.usuario()).orElseThrow(() -> new NaoEncontrado("Usuario nao encontrado"));

        reserva.setSala(sala);
        reserva.setUsuario(usuario);
        reserva.setHoraInicio(dto.horaInicio());
        reserva.setHoraFinal(dto.horaFinal());

        return reservaRepository.save(reserva);
    }

    public void deletarReserva(Long id){
        if (!reservaRepository.existsById(id)){
            throw new NaoEncontrado("Reserva nao encontrada");
        }
        reservaRepository.deleteById(id);
    }
}

