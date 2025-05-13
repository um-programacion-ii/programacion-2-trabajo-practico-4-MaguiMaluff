package com.example.TP_4.Servicios;

import com.example.TP_4.Modelos.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorNombre(String nombre);
    Usuario buscarPorId(Long id);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}
