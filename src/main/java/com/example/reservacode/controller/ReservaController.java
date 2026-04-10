package com.example.reservacode.controller;

import com.example.reservacode.dto.request.ReservaRequestDTO;
import com.example.reservacode.dto.response.ReservaRespondDTO;
import com.example.reservacode.entity.Reserva;
import com.example.reservacode.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {


    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping()
    public ResponseEntity<ReservaRespondDTO> reservarSala(@RequestBody ReservaRequestDTO response){
        ReservaRespondDTO responseDTO = reservaService.criarReserva(response);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaRespondDTO>> listarReservas(){
        List<ReservaRespondDTO> listAll = reservaService.listarReservas();
        return ResponseEntity.ok(listAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaRespondDTO> buscarPorId(@PathVariable Long id){
        ReservaRespondDTO reservaPorID = reservaService.buscarId(id);
        return ResponseEntity.ok(reservaPorID);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@RequestBody ReservaRequestDTO dto, @PathVariable Long id){
        reservaService.atualizarReserva(dto,id);
        Reserva reserva = reservaService.atualizarReserva(dto,id);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id){
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();

    }
}
