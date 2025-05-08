package Repositorios;

import Modelos.Prestamo;
import Modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findByUser(Usuario usuario);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
