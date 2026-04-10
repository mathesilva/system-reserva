package com.example.reservacode.controller;


import com.example.reservacode.dto.request.UsuarioRequestDTO;
import com.example.reservacode.dto.response.UsuarioRespondDTO;
import com.example.reservacode.entity.Usuario;
import com.example.reservacode.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioRespondDTO> criarUsuario(@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioRespondDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespondDTO>>listarUsuarios(){
        List<UsuarioRespondDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespondDTO>buscarPorID(@PathVariable Long id){
        UsuarioRespondDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUser(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto){
        usuarioService.atualizarUser(id, dto);
        Usuario usuario = usuarioService.atualizarUser(id,dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id){
        usuarioService.deletarUser(id);
        return ResponseEntity.noContent().build();
    }

}
