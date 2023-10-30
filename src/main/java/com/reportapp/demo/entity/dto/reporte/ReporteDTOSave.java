package com.reportapp.demo.entity.dto.reporte;

import com.reportapp.demo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTOSave {

    private Long id;
    private Long barrioId;
    private Long tipoReporteId;
    private Imagen imagen;
    private Coordenadas coordenadas;
    private LocalDate fechaRegistro;
    private String descripcion;
}
