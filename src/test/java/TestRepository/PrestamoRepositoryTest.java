package TestRepository;

import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Repositorios.PrestamoRepository;
import com.example.TP_4.RepositoriosImpl.PrestamoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoRepositoryTest {

    private PrestamoRepository prestamoRepository;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();
    }

    @Test
    void save_DeberiaGuardarPrestamoConId() {
        Prestamo prestamo = new Prestamo();
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        prestamo.setUsuario(usuario);

        Prestamo guardado = prestamoRepository.save(prestamo);

        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getUsuario().getNombre());
    }

    @Test
    void findById_DeberiaRetornarPrestamoSiExiste() {
        Prestamo prestamo = new Prestamo();
        Usuario usuario = new Usuario();
        usuario.setNombre("Ana");
        prestamo.setUsuario(usuario);
        prestamoRepository.save(prestamo);

        Optional<Prestamo> resultado = prestamoRepository.findById(prestamo.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Ana", resultado.get().getUsuario().getNombre());
    }

    @Test
    void findById_DeberiaRetornarVacioSiNoExiste() {
        Optional<Prestamo> resultado = prestamoRepository.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void findByUser_DeberiaRetornarPrestamoSiExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamoRepository.save(prestamo);

        Optional<Prestamo> resultado = prestamoRepository.findByUser(usuario);

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getUsuario().getNombre());
    }

    @Test
    void findByUser_DeberiaRetornarVacioSiNoExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("NoExiste");

        Optional<Prestamo> resultado = prestamoRepository.findByUser(usuario);

        assertFalse(resultado.isPresent());
    }

    @Test
    void findAll_DeberiaRetornarTodosLosPrestamos() {
        Prestamo p1 = new Prestamo();
        p1.setUsuario(new Usuario());
        prestamoRepository.save(p1);

        Prestamo p2 = new Prestamo();
        p2.setUsuario(new Usuario());
        prestamoRepository.save(p2);

        List<Prestamo> resultado = prestamoRepository.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void deleteById_DeberiaEliminarPrestamo() {
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(new Usuario());
        prestamoRepository.save(prestamo);

        prestamoRepository.deleteById(prestamo.getId());

        Optional<Prestamo> resultado = prestamoRepository.findById(prestamo.getId());
        assertFalse(resultado.isPresent());
    }

    @Test
    void existsById_DeberiaRetornarTrueSiExiste() {
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(new Usuario());
        prestamoRepository.save(prestamo);

        assertTrue(prestamoRepository.existsById(prestamo.getId()));
    }

    @Test
    void existsById_DeberiaRetornarFalseSiNoExiste() {
        assertFalse(prestamoRepository.existsById(123L));
    }
}
