package com.reportapp.demo.entity.dto.reporte;

import com.reportapp.demo.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReporteDTOSave {

    private Long id;
    private Long  usuarioId;
    private Long barrioId;
    private Long tipoReporteId;
    private Imagen imagen;
    private Coordenadas coordenadas;
    private LocalDate fechaRegistro;
    private String descripcion;
}
