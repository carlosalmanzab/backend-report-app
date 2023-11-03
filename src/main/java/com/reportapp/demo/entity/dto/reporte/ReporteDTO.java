package com.reportapp.demo.entity.dto.reporte;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

    @JsonProperty("fechaRegistro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaRegistro;

    private String descripcion;
}
