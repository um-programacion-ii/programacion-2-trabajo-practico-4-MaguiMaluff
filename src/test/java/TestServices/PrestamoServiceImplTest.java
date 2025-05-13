package TestServices;

import com.example.TP_4.Exepciones.PrestamoNoEncontradoException;
import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Repositorios.PrestamoRepository;
import com.example.TP_4.ServiciosImpl.PrestamoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void buscarPorUsuario_DeberiaRetornarPrestamoSiExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);

        when(prestamoRepository.findByUser(usuario)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado.getUsuario());
        verify(prestamoRepository).findByUser(usuario);
    }

    @Test
    void buscarPorUsuario_DeberiaLanzarExcepcionSiNoExiste() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");

        when(prestamoRepository.findByUser(usuario)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.buscarPorUsuario(usuario));
    }

    @Test
    void buscarPorId_DeberiaRetornarPrestamoSiExiste() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);

        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void buscarPorId_DeberiaLanzarExcepcionSiNoExiste() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.buscarPorId(1L));
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDePrestamos() {
        Prestamo prestamo = new Prestamo();
        List<Prestamo> prestamos = Collections.singletonList(prestamo);

        when(prestamoRepository.findAll()).thenReturn(prestamos);

        List<Prestamo> resultado = prestamoService.obtenerTodos();

        assertEquals(1, resultado.size());
    }

    @Test
    void guardar_DeberiaGuardarYRetornarPrestamo() {
        Prestamo prestamo = new Prestamo();

        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.guardar(prestamo);

        assertNotNull(resultado);
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void eliminar_DeberiaLlamarDeleteById() {
        prestamoService.eliminar(1L);

        verify(prestamoRepository).deleteById(1L);
    }

    @Test
    void actualizar_DeberiaActualizarYRetornarPrestamoSiExiste() {
        Prestamo prestamo = new Prestamo();

        when(prestamoRepository.existsById(1L)).thenReturn(true);
        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo resultado = prestamoService.actualizar(1L, prestamo);

        assertNotNull(resultado);
        assertEquals(1L, prestamo.getId());
        verify(prestamoRepository).save(prestamo);
    }

    @Test
    void actualizar_DeberiaLanzarExcepcionSiNoExiste() {
        Prestamo prestamo = new Prestamo();

        when(prestamoRepository.existsById(1L)).thenReturn(false);

        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.actualizar(1L, prestamo));
    }
}
