package com.example.reservacode.service;

import com.example.reservacode.dto.request.SalaRequestDTO;
import com.example.reservacode.dto.response.SalaResponseDTO;
import com.example.reservacode.entity.Sala;
import com.example.reservacode.exceptions.NaoEncontrado;
import com.example.reservacode.exceptions.SalaOcupada;
import com.example.reservacode.repository.ReservaRepository;
import com.example.reservacode.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public SalaService(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public Sala criarSala(SalaRequestDTO dto){
       Optional<Sala> sala = salaRepository.findByNome(dto.nome());
       if (sala.isPresent()){
        throw new SalaOcupada("Esta sala ja existe");
       }

       Sala salaCriada = new Sala();
       salaCriada.setNome(dto.nome());
       salaCriada.setCapacidade(dto.capacidade());
       salaCriada.setId(dto.id());
       salaCriada.setAtiva(true);
       return salaRepository.save(salaCriada);
    }

    public List<SalaResponseDTO> listarTodas(){
        List<Sala> listaAll = salaRepository.findAll();
        return listaAll.stream().map(SalaResponseDTO::new).toList();
        }


    public SalaResponseDTO bucarId(Long id){
        Sala salaId = salaRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Sala nao encontrada"));
        return new SalaResponseDTO(salaId);
    }


    public Sala atualizarSala(SalaRequestDTO dto, Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Sala nao encontrada"));
        sala.setNome(dto.nome());
        sala.setCapacidade(dto.capacidade());
        return salaRepository.save(sala);
    }

    public void deletarSala(Long id){

        Sala sala = salaRepository.findById(id).orElseThrow(()-> new RuntimeException("Sala nao encontrada"));

        if (reservaRepository.existsBySala(sala)){
            throw new NaoEncontrado("Não é possível excluir esta sala. Existem reservas vinculadas a ela. Considere inativá-la.\"");
        }
         salaRepository.delete(sala);
    }
}
