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
    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String apellido;

    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String img;

}
