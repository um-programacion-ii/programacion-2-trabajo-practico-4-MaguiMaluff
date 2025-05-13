package TestControllers;

import com.example.TP_4.Exepciones.UsuarioNoEncontradoException;
import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Servicios.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerTodosTest() throws Exception {
        Usuario usuario1 = new Usuario(1L, "Pepe", "pepe@example.com");
        Usuario usuario2 = new Usuario(2L, "Juana", "juana@example.com");

        when(usuarioService.obtenerTodos()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/api/usuarios/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Pepe"))
                .andExpect(jsonPath("$[0].email").value("pepe@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Juana"))
                .andExpect(jsonPath("$[1].email").value("juana@example.com"));
    }

    @Test
    void obtenerTodosCuandoNoHayUsuariosTest() throws Exception {
        when(usuarioService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/usuarios/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void obtenerPorIdTest() throws Exception {
        Long usuarioId = 1L;
        Usuario usuario = new Usuario(usuarioId, "Pepe", "pepe@example.com");
        when(usuarioService.buscarPorId(usuarioId)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/" + usuarioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioId))
                .andExpect(jsonPath("$.nombre").value("Pepe"))
                .andExpect(jsonPath("$.email").value("pepe@example.com"));
    }


    @Test
    void crearTest() throws Exception {
        Usuario usuarioACrear = new Usuario(null, "Carlos", "carlos@example.com");
        Usuario usuarioCreado = new Usuario(3L, "Carlos", "carlos@example.com");
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuarioCreado);

        mockMvc.perform(post("/api/usuarios/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioACrear)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.email").value("carlos@example.com"));
    }

    @Test
    void actualizarTest() throws Exception {
        Long usuarioId = 1L;
        Usuario usuarioActualizar = new Usuario(usuarioId, "Ana", "ana@example.com");
        when(usuarioService.actualizar(eq(usuarioId), any(Usuario.class))).thenReturn(usuarioActualizar);

        mockMvc.perform(put("/api/usuarios/" + usuarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioActualizar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioId))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@example.com"));
    }


    @Test
    void eliminarTest() throws Exception {
        Long usuarioId = 1L;
        doNothing().when(usuarioService).eliminar(usuarioId);

        mockMvc.perform(delete("/api/usuarios/" + usuarioId))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarNoEncontradoImpliedTest() throws Exception {
        Long usuarioId = 99L;
        doNothing().when(usuarioService).eliminar(usuarioId);

        mockMvc.perform(delete("/api/usuarios/" + usuarioId))
                .andExpect(status().isOk());
    }
}