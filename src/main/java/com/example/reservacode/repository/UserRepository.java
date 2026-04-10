package com.example.reservacode.repository;

import com.example.reservacode.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);
    void deleteById(Long id);

    Optional<Object> findByIdAndEmail(Long id, String email);
}
