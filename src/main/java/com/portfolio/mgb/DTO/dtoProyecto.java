package com.portfolio.mgb.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class dtoProyecto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;

    public dtoProyecto(){}

    public dtoProyecto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
