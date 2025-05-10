package Servicios;

import Modelos.Prestamo;
import Modelos.Usuario;

import java.util.List;

public interface PrestamoService {
    Prestamo buscarPorUsuario(Usuario usuario);
    Prestamo buscarPorId(Long id);
    List<Prestamo> obtenerTodos();
    Prestamo guardar(Prestamo prestamo);
    void eliminar(Long id);
    Prestamo actualizar(Long id, Prestamo prestamo);
}
