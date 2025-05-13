package com.example.TP_4.Controladores;

import com.example.TP_4.Modelos.Prestamo;
import com.example.TP_4.Servicios.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /**
     * Obtener todos los préstamos registrados.
     *
     * @return Lista de préstamos.
     */
    @GetMapping("todos")
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    /**
     * Obtener un préstamo por su ID.
     *
     * @param id ID del préstamo a buscar.
     * @return El préstamo correspondiente al ID.
     */
    @GetMapping("{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    /**
     * Crear un nuevo préstamo.
     *
     * @param prestamo Objeto Prestamo a crear.
     * @return El préstamo creado con su ID asignado.
     */
    @PostMapping("crear")
    public Prestamo crear(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    /**
     * Actualizar un préstamo existente.
     *
     * @param id ID del préstamo a actualizar.
     * @param prestamo Objeto Prestamo con los nuevos datos.
     * @return El préstamo actualizado.
     */
    @PutMapping("{id}")
    public Prestamo actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizar(id, prestamo);
    }

    /**
     * Eliminar un préstamo por su ID.
     *
     * @param id ID del préstamo a eliminar.
     */
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}
