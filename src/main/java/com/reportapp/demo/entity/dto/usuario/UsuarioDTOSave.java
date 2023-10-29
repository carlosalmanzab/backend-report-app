package com.reportapp.demo.entity.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Long barrioId;
}
