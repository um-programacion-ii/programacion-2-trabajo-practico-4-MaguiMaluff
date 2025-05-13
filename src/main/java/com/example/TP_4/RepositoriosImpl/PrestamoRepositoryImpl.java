package com.example.TP_4.RepositoriosImpl;

import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Repositorios.PrestamoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {
    private final Map<Long, Prestamo> prestamos = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(nextId++);
        }
        prestamos.put(prestamo.getId(), prestamo);
        return prestamo;
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return Optional.ofNullable(prestamos.get(id));
    }

    @Override
    public Optional<Prestamo> findByUser(Usuario usuario) {
        return prestamos.values().stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .findFirst();
    }

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(prestamos.values());
    }

    @Override
    public void deleteById(Long id) {
        prestamos.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return prestamos.containsKey(id);
    }
}

