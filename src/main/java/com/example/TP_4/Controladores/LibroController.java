package com.example.TP_4.Controladores;

import com.example.TP_4.Modelos.Libro;
import com.example.TP_4.Servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


    /**
     * Obtener todos los libros disponibles.
     *
     * @return Lista de libros.
     */
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    /**
     * Obtener un libro por su ID.
     *
     * @param id ID del libro a buscar.
     * @return El libro correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    /**
     * Crear un nuevo libro.
     *
     * @param libro Objeto Libro a crear.
     * @return El libro creado con su ID asignado.
     */
    @PostMapping("/crear")
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    /**
     * Actualizar un libro existente.
     *
     * @param id ID del libro a actualizar.
     * @param libro Objeto Libro con los nuevos datos.
     * @return El libro actualizado.
     */
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }

    /**
     * Eliminar un libro por su ID.
     *
     * @param id ID del libro a eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}

