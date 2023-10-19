package com.reportapp.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

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

    @NotBlank
    @Column(name = "documento", unique = true)
    private String identificacion;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "segundoNombre")
    private String segundoNombre;

    @NotBlank(message = "El campo apellido no puede estar vacio")
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "segundoApellido")
    private String segundoApellido;

    @NotBlank(message = "El campo email no puede estar vacio")
    @Email(message =  "El email ingresado no cumple con el formato standar")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "El campo password no puede estar vacio")
    @Length(min = 6, message = "La contraseña debe tener minimo 6 caracteres")
    @Column(name = "password")
    private String password;

    @Column(name = "celular")
    private String celular;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    @OneToMany(mappedBy = "usuario")
    private Set<Reporte> reportes;

}
