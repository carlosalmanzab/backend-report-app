package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.entity.usuario.Usuario;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    Usuario toEntity(UsuarioDTOSave usuarioDTOSave);

    default List<ReporteDTO> toReporteDTOList(List<Reporte> reportes) {
        if (reportes == null) {
            return new ArrayList<>();
        }
        return reportes.stream()
                .map(reporte -> {
                    if (reporte == null) {
                        return null;
                    }
                    return toReporteDTO(reporte);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    default List<Reporte> toReporteList(List<ReporteDTO> reporteDTOs) {
        if (reporteDTOs == null) {
            return new ArrayList<>();
        }
        return reporteDTOs.stream()
                .map(reporteDTO -> {
                    if (reporteDTO == null) {
                        return null;
                    }
                    return toReporte(reporteDTO);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    ReporteDTO toReporteDTO(Reporte reporte);

    Reporte toReporte(ReporteDTO reporteDTO);

    default UsuarioDTO toDTOWithReportes(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = toDTO(usuario);
        usuarioDTO.setReportes(toReporteDTOList(usuario.getReportes())); // Llama al método que mapea la lista de Reportes
        return usuarioDTO;
    }

}
