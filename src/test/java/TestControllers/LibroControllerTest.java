package TestControllers;

import com.example.TP_4.Modelos.Libro;
import com.example.TP_4.Servicios.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = com.example.TP_4.Tp4Application.class)
@AutoConfigureMockMvc
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GETobtenerTodosTest() throws Exception {
        Libro libro1 = new Libro(1L, "Libro A", "Autor A", "222");
        Libro libro2 = new Libro(2L, "Libro B", "Autor B", "333");

        libroService.guardar(libro1);
        libroService.guardar(libro2);

        when(libroService.obtenerTodos()).thenReturn(Arrays.asList(libro1, libro2));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Libro A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Libro B"));
    }

    @Test
    void obtenerTodosCuandoNoHayLibrosTest() throws Exception {
        when(libroService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void obtenerPorIdTest() throws Exception {
        Libro libro = new Libro(1L, "Libro A", "Autor A", "222");
        libroService.guardar(libro);
        when(libroService.buscarPorId(1L)).thenReturn(libro);

        mockMvc.perform(get("/api/libros/1")) // La ruta ahora incluye el ID directamente
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Libro A"));
    }

    @Test
    void crearTest() throws Exception {
        Libro libroACrear = new Libro(null, "Nuevo Libro", "Nuevo Autor", "444");
        Libro libroCreado = new Libro(3L, "Nuevo Libro", "Nuevo Autor", "444");
        libroService.guardar(libroACrear);
        libroService.guardar(libroCreado);
        when(libroService.guardar(any(Libro.class))).thenReturn(libroCreado);

        mockMvc.perform(post("/api/libros/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libroACrear)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titulo").value("Nuevo Libro"))
                .andExpect(jsonPath("$.autor").value("Nuevo Autor"))
                .andExpect(jsonPath("$.isbn").value("444"));
    }

    @Test
    void actualizarTest() throws Exception {
        Libro libroActualizar = new Libro(1L, "Libro Actualizado", "Autor B", "555");

        libroService.guardar(libroActualizar);

        when(libroService.actualizar(eq(1L), any(Libro.class))).thenReturn(libroActualizar);

        mockMvc.perform(put("/api/libros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libroActualizar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Libro Actualizado"))
                .andExpect(jsonPath("$.autor").value("Autor B"))
                .andExpect(jsonPath("$.isbn").value("555"));
    }

    @Test
    void eliminarTest() throws Exception {
        Libro libro1 = new Libro(1L, "Libro A", "Autor A", "222");
        libroService.guardar(libro1);
        doNothing().when(libroService).eliminar(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isOk());
    }
}