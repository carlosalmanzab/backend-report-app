package com.reportapp.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "documento", unique = true)
    private String identificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "segundoNombre")
    private String segundoNombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "segundoApellido")
    private String segundoApellido;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "celular")
    private String celular;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    @ManyToMany
    @JoinTable(name = "usuario_reporte",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "reporte_id"))
    private List<Reporte> reportes;


}
