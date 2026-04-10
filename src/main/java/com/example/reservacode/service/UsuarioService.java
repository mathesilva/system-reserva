package com.example.reservacode.service;

import com.example.reservacode.dto.request.UsuarioRequestDTO;
import com.example.reservacode.dto.response.UsuarioRespondDTO;
import com.example.reservacode.entity.Usuario;
import com.example.reservacode.exceptions.EmailJaExiste;
import com.example.reservacode.exceptions.NaoEncontrado;
import com.example.reservacode.repository.ReservaRepository;
import com.example.reservacode.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UserRepository userRepository;
    private final ReservaRepository reservaRepository;

    public UsuarioService(UserRepository userRepository, ReservaRepository reservaRepository) {
        this.userRepository = userRepository;
        this.reservaRepository = reservaRepository;
    }

    public Usuario criarUsuario(UsuarioRequestDTO dto) {

        Optional<Usuario> usuarioOptional = userRepository.findByEmail(dto.email());
        if (usuarioOptional.isPresent()) {
            throw new EmailJaExiste("email ja cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        return userRepository.save(usuario);
    }

    public List<UsuarioRespondDTO> listarUsuarios() {
        List<Usuario> listaUsuarios = userRepository.findAll();
        return listaUsuarios.stream().map(UsuarioRespondDTO::new).toList();
    }

    public UsuarioRespondDTO buscarPorId(Long id) {
        Usuario buscarId = userRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Usuario nao encontrado"));
        return new UsuarioRespondDTO(buscarId);
    }

    public Usuario atualizarUser(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = userRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Usuario nao encontrado"));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        return userRepository.save(usuario);

    }

    public void deletarUser(Long id) {
        Usuario usuario = userRepository.findById(id).orElseThrow(()-> new NaoEncontrado("Usuario nao encontrado"));
        if (reservaRepository.existsByUsuario(usuario)) {
            throw new NaoEncontrado("Voce tem uma reserva agendada ou em andamento");
        }
        userRepository.delete(usuario);
    }
}