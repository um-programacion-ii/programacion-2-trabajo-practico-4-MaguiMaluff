package TestServices;

import com.example.TP_4.Exepciones.UsuarioNoEncontradoException;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Repositorios.UsuarioRepository;
import com.example.TP_4.ServiciosImpl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void cuandoBuscarPorNombreExiste_entoncesRetornaUsuario() {
        String nombre = "Juan Pérez";
        Usuario usuarioEsperado = new Usuario(1L, nombre, "jp@ejemplo.com");
        when(usuarioRepository.findByName(nombre)).thenReturn(Optional.of(usuarioEsperado));

        Usuario resultado = usuarioService.buscarPorNombre(nombre);

        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
        verify(usuarioRepository).findByName(nombre);
    }

    @Test
    void cuandoBuscarPorNombreNoExiste_entoncesLanzaExcepcion() {
        String nombre = "Juan Pérez";
        when(usuarioRepository.findByName(nombre)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuarioService.buscarPorNombre(nombre)
        );
    }

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaUsuario() {
        Long id = 1L;
        Usuario usuarioEsperado = new Usuario(id, "Juan Pérez", "jpo@ejemplo.com");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEsperado));

        Usuario resultado = usuarioService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).findById(id);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuarioService.buscarPorId(id)
        );
    }

    @Test
    void cuandoGuardarUsuario_entoncesRetornaUsuarioGuardado() {
        Usuario usuario = new Usuario(null, "Juan Pérez", "jp@ejemplo.com");
        Usuario usuarioGuardado = new Usuario(1L, "Juan Pérez", "jp@ejemplo.com");
        when(usuarioRepository.save(usuario)).thenReturn(usuarioGuardado);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoActualizarUsuarioNoExiste_entoncesLanzaExcepcion() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Juan Pérez", "jp@ejemplo.com");
        when(usuarioRepository.existsById(id)).thenReturn(false);

        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuarioService.actualizar(id, usuario)
        );
    }

    @Test
    void cuandoActualizarUsuario_entoncesRetornaUsuarioActualizado() {
    Long id = 1L;
        Usuario usuario = new Usuario(id, "Juan Pérez", "correo@ejemplo.com");
        Usuario usuarioActualizado = new Usuario(id, "Juan Pérez", "nuevo@correo.com");
        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.save(usuario)).thenReturn(usuarioActualizado);

        Usuario resultado = usuarioService.actualizar(id, usuario);

        assertNotNull(resultado);
        assertEquals("nuevo@correo.com", resultado.getEmail());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void cuandoEliminarUsuario_entoncesEliminaUsuario() {
        Long id = 1L;

        usuarioService.eliminar(id);

        verify(usuarioRepository).deleteById(id);
    }
}