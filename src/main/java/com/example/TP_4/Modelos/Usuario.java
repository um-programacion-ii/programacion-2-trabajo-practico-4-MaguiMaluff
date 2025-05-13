package com.example.TP_4.Modelos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estado;

    public Usuario(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = EstadoUsuario.DISPONIBLE;
    }

    public enum EstadoUsuario{
        PRESTAMO,
        EN_DEUDA,
        DISPONIBLE

    }
}
