package com.example.TP_4.Controladores;

import com.example.TP_4.Modelos.Usuario;
import com.example.TP_4.Servicios.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtener todos los usuarios registrados.
     *
     * @return Lista de usuarios.
     */
    @GetMapping("todos")
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    /**
     * Obtener un usuario por su ID.
     *
     * @param id ID del usuario a buscar.
     * @return El usuario correspondiente al ID.
     */
    @GetMapping("{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    /**
     * Crear un nuevo usuario.
     *
     * @param usuario Objeto Usuario a crear.
     * @return El usuario creado con su ID asignado.
     */
    @PostMapping("crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }


    /**
     * Actualizar un usuario existente.
     *
     * @param id ID del usuario a actualizar.
     * @param usuario Objeto Usuario con los nuevos datos.
     * @return El usuario actualizado.
     */
    @PutMapping("{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    /**
     * Eliminar un usuario por su ID.
     *
     * @param id ID del usuario a eliminar.
     */
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

}

