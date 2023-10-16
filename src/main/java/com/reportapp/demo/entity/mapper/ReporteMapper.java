package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.Reporte;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    ReporteDTO toDTO(Reporte reporte);
    Reporte toEntity(ReporteDTO reporteDTO);

    List<ReporteDTO> toReporteDTOList(List<Reporte> reportes); // Mapeo de la lista de Reportes

    List<Reporte> toReporteList(List<ReporteDTO> reporteDTOs);
}
