package com.reportapp.demo.entity.dto.tipoReporte;

import jakarta.persistence.Column;

public class TipoReporteDTOSave {
    private Long id;
    private String nombre;
    private String descripcion;
    private String nombreUsuario;
    private String fecha;
    private boolean estado;
}
