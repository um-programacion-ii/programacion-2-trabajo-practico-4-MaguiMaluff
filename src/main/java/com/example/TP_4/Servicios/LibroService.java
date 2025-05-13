package com.example.TP_4.Servicios;

import com.example.TP_4.Modelos.Libro;

import java.util.*;

public interface LibroService {
    Libro buscarPorIsbn(String isbn);
    Libro buscarPorId(Long id);
    List<Libro> obtenerTodos();
    Libro guardar(Libro libro);
    void eliminar(Long id);
    Libro actualizar(Long id, Libro libro);
}
