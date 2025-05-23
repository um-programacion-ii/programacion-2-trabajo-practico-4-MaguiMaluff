package com.example.TP_4.Modelos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter

public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private EstadoLibro estado;
    private String isbn;

    public Libro(Long id, String titulo, String autor, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public enum EstadoLibro{
        DISPONIBLE,
        PRESTADO,
        EN_REPARACION
    }

}
