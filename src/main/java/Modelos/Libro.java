package Modelos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter

public class Libro {
    private long id;
    private String titulo;
    private String autor;
    private EstadoLibro estado;

    public Libro(long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public enum EstadoLibro{
        DISPONIBLE,
        PRESTADO,
        EN_REPARACION
    }

}
