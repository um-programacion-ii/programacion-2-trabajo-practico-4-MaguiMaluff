package com.example.TP_4.Servicios;

import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Modelos.Usuario;

import java.util.List;

public interface PrestamoService {
    Prestamo buscarPorUsuario(Usuario usuario);
    Prestamo buscarPorId(Long id);
    List<Prestamo> obtenerTodos();
    Prestamo guardar(Prestamo prestamo);
    void eliminar(Long id);
    Prestamo actualizar(Long id, Prestamo prestamo);
}
