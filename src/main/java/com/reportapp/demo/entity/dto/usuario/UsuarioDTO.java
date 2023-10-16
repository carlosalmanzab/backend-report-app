package com.reportapp.demo.entity.dto.usuario;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    private String identificacion;

    private String nombre;

    private String segundoNombre;

    private String apellido;

    private String segundoApellido;

    private String email;

    private String celular;

    private String direccion;

    private List<ReporteDTO> reportes;
}
