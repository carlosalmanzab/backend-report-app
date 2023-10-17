package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ReporteMapper {

    ReporteMapper INSTANCE = Mappers.getMapper(ReporteMapper.class);
    ReporteDTO toDTO(Reporte reporte);
    Reporte toEntity(ReporteDTO reporteDTO);

    Reporte toEntity(ReporteDTOSave reporteDTOSave);

}
