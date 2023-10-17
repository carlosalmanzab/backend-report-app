package com.reportapp.demo.entity.dto.reporte;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Coordenadas;
import com.reportapp.demo.entity.Imagen;
import com.reportapp.demo.entity.TipoReporte;
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

    private Barrio barrio;

    private TipoReporte tipoReporte;

    private Imagen imagen;

    private Coordenadas coordenadas;

    private LocalDate fechaRegistro;

    private String descripcion;
}
