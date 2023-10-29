package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.TipoReporte;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTO;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TipoReporteMapper {

    TipoReporteMapper INSTANCE = Mappers.getMapper(TipoReporteMapper.class);
    TipoReporteDTO toDTO(TipoReporte tipoReporte);
    TipoReporte toEntity(TipoReporteDTO tipoReporteDTO);
    TipoReporte toEntity(TipoReporteDTOSave tipoReporteDTOSave);
}
