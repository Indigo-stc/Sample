package com.ista.sample.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "clave")
    private String clave;
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private Boolean estado;

}
