package com.example.TP_4.Repositorios;

import com.example.TP_4.Modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByName(String name);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
