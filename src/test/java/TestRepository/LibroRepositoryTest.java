package TestRepository;

import Modelos.Libro;
import Repositorios.LibroRepository;
import RepositoriosImpl.LibroRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryTest {

    private LibroRepository libroRepository;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void save_DeberiaGuardarLibroConId() {
        Libro libro = new Libro();
        libro.setTitulo("Mi Libro");
        libro.setIsbn("123456");

        Libro guardado = libroRepository.save(libro);

        assertNotNull(guardado.getId());
        assertEquals("Mi Libro", guardado.getTitulo());
    }

    @Test
    void findById_DeberiaRetornarLibroSiExiste() {
        Libro libro = new Libro();
        libro.setTitulo("Libro 1");
        libroRepository.save(libro);

        Optional<Libro> resultado = libroRepository.findById(libro.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Libro 1", resultado.get().getTitulo());
    }

    @Test
    void findById_DeberiaRetornarVacioSiNoExiste() {
        Optional<Libro> resultado = libroRepository.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void findByIsbn_DeberiaRetornarLibroSiExiste() {
        Libro libro = new Libro();
        libro.setIsbn("ABC123");
        libroRepository.save(libro);

        Optional<Libro> resultado = libroRepository.findByIsbn("ABC123");

        assertTrue(resultado.isPresent());
        assertEquals("ABC123", resultado.get().getIsbn());
    }

    @Test
    void findByIsbn_DeberiaRetornarVacioSiNoExiste() {
        Optional<Libro> resultado = libroRepository.findByIsbn("NO_EXISTE");

        assertFalse(resultado.isPresent());
    }

    @Test
    void findAll_DeberiaRetornarTodosLosLibros() {
        Libro libro1 = new Libro();
        libro1.setTitulo("Libro 1");
        libroRepository.save(libro1);

        Libro libro2 = new Libro();
        libro2.setTitulo("Libro 2");
        libroRepository.save(libro2);

        List<Libro> resultado = libroRepository.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void deleteById_DeberiaEliminarLibro() {
        Libro libro = new Libro();
        libroRepository.save(libro);

        libroRepository.deleteById(libro.getId());

        Optional<Libro> resultado = libroRepository.findById(libro.getId());
        assertFalse(resultado.isPresent());
    }

    @Test
    void existsById_DeberiaRetornarTrueSiExiste() {
        Libro libro = new Libro();
        libroRepository.save(libro);

        assertTrue(libroRepository.existsById(libro.getId()));
    }

    @Test
    void existsById_DeberiaRetornarFalseSiNoExiste() {
        assertFalse(libroRepository.existsById(123L));
    }
}

