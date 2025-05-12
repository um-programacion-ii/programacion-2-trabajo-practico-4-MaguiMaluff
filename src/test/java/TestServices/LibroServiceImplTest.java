package TestServices;

import Exepciones.LibroNoEncontradoException;
import Modelos.Libro;
import Repositorios.LibroRepository;
import ServiciosImpl.LibroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        String isbn = "1864-2846-5894";
        Libro libroEsperado = new Libro(1L, "Harry Potter", "JK", isbn);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));

        Libro resultado = libroService.buscarPorIsbn(isbn);

        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        String isbn = "168614-64615";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        assertThrows(LibroNoEncontradoException.class, () ->
                libroService.buscarPorIsbn(isbn)
        );
    }
}