package TestControllers;

import com.example.TP_4.Modelos.Libro;
import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Servicios.PrestamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
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
public class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @Autowired
    private ObjectMapper objectMapper;
    private Usuario usuario;
    private Libro libro;
    private Usuario usuario2;
    private Libro libro2;

    @BeforeEach
    public void setUp(){
        this.usuario = new Usuario(1L, "Pepe", "@");
        this.libro = new Libro(1L, "NombreLibro", "Juana", "789");
        this.usuario2 = new Usuario(2L, "Pepa", "@");
        this.libro2 = new Libro(2L, "Nombre2", "Juan", "859");
    }

    @Test
    void obtenerTodosTest() throws Exception {
        Prestamo prestamo1 = new Prestamo(1L, this.libro, this.usuario);
        Prestamo prestamo2 = new Prestamo(2L, this.libro2, this.usuario2);

        when(prestamoService.obtenerTodos()).thenReturn(Arrays.asList(prestamo1, prestamo2));

        mockMvc.perform(get("/api/prestamos/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void obtenerTodosCuandoNoHayPrestamosTest() throws Exception {
        when(prestamoService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/prestamos/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void obtenerPorIdTest() throws Exception {
        Prestamo prestamo1 = new Prestamo(1L, this.libro, this.usuario);
        when(prestamoService.buscarPorId(1L)).thenReturn(prestamo1);

        mockMvc.perform(get("/api/prestamos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void crearTest() throws Exception {
        Prestamo prestamoACrear = new Prestamo(null, this.libro, this.usuario);
        Prestamo prestamoCreado = new Prestamo(3L, this.libro, this.usuario);
        when(prestamoService.guardar(any(Prestamo.class))).thenReturn(prestamoCreado);

        mockMvc.perform(post("/api/prestamos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamoACrear)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void actualizarTest() throws Exception {
        Prestamo prestamoActualizar = new Prestamo(1L, this.libro2, this.usuario);
        when(prestamoService.actualizar(eq(1L), any(Prestamo.class))).thenReturn(prestamoActualizar);

        mockMvc.perform(put("/api/prestamos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamoActualizar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void eliminarTest() throws Exception {
        doNothing().when(prestamoService).eliminar(1L);

        mockMvc.perform(delete("/api/prestamos/1"))
                .andExpect(status().isOk());
    }
}