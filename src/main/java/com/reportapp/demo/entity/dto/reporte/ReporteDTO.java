package com.reportapp.demo.entity.dto.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    private Long id;

    private Long barrioId;

    private Long tipoReporteId;

    private Long imagenId;

    private Long coordenadaId;

    private LocalDate fechaRegistro;

    private String descripción;
}
