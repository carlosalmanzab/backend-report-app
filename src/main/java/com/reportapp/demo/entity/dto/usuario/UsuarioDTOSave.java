package com.reportapp.demo.entity.dto.usuario;

import com.reportapp.demo.entity.Barrio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class UsuarioDTOSave {

    private Long id;
    private String identificacion;
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String email;
    private String password;
    private String celular;
    private String direccion;
    private Barrio barrio;
}
