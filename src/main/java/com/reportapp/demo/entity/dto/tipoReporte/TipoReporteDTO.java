package com.reportapp.demo.entity.dto.tipoReporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoReporteDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
}
