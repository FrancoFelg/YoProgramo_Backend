package com.portfolio.mgb.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String apellido;

    @NotNull
    private String descripcion;

    private String img;

    private String imgFondo;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String descripcion, String img, String imgFondo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.img = img;
        this.imgFondo = imgFondo;
    }
}
