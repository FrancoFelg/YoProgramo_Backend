package com.portfolio.mgb.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class dtoPersona {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String img;
    @NotBlank
    private String imgFondo;

    public dtoPersona(){}

    public dtoPersona(String nombre, String apellido, String descripcion, String img, String imgFondo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.img = img;
        this.imgFondo = imgFondo;
    }

}
