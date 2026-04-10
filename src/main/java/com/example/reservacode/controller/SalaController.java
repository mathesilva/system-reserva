package com.example.reservacode.controller;

import com.example.reservacode.dto.request.SalaRequestDTO;
import com.example.reservacode.dto.response.SalaResponseDTO;
import com.example.reservacode.entity.Sala;
import com.example.reservacode.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> criarSala(@RequestBody SalaRequestDTO dto){
        Sala sala = salaService.criarSala(dto);
        return ResponseEntity.ok(new SalaResponseDTO(sala));
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listarSalas(){
        List<SalaResponseDTO> listaAll = salaService.listarTodas();
        return ResponseEntity.ok(listaAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> idSala(@PathVariable Long id){
        SalaResponseDTO salaDto = salaService.bucarId(id);
        return ResponseEntity.ok(salaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> updateSala(@RequestBody SalaRequestDTO dto, @PathVariable Long id){
        Sala updateSala = salaService.atualizarSala(dto,id);
        return ResponseEntity.ok(updateSala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id){
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();

    }



}
