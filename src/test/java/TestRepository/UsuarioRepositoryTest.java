package TestRepository;

import Modelos.Usuario;
import Repositorios.UsuarioRepository;
import RepositoriosImpl.UsuarioRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void save_DeberiaGuardarUsuarioConId() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");

        Usuario guardado = usuarioRepository.save(usuario);

        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getNombre());
    }

    @Test
    void findById_DeberiaRetornarUsuarioSiExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Ana");
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Ana", resultado.get().getNombre());
    }

    @Test
    void findById_DeberiaRetornarVacioSiNoExiste() {
        Optional<Usuario> resultado = usuarioRepository.findById(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void findByName_DeberiaRetornarUsuarioSiExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByName("Carlos");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNombre());
    }

    @Test
    void findByName_DeberiaRetornarVacioSiNoExiste() {
        Optional<Usuario> resultado = usuarioRepository.findByName("NoExiste");

        assertFalse(resultado.isPresent());
    }

    @Test
    void findAll_DeberiaRetornarTodosLosUsuarios() {
        Usuario u1 = new Usuario();
        u1.setNombre("A");
        usuarioRepository.save(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("B");
        usuarioRepository.save(u2);

        List<Usuario> resultado = usuarioRepository.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    void deleteById_DeberiaEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Eliminar");
        usuarioRepository.save(usuario);

        usuarioRepository.deleteById(usuario.getId());

        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());
        assertFalse(resultado.isPresent());
    }

    @Test
    void existsById_DeberiaRetornarTrueSiExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Existe");
        usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsById(usuario.getId()));
    }

    @Test
    void existsById_DeberiaRetornarFalseSiNoExiste() {
        assertFalse(usuarioRepository.existsById(123L));
    }
}
