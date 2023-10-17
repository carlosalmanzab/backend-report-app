package com.reportapp.demo.entity.mapper;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.Usuario;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTOLogin usuarioDTOLogin);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "barrio", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    Usuario toEntity(UsuarioDTOSave usuarioDTOSave);

    default List<ReporteDTO> toReporteDTOList(List<Reporte> reportes) {
        return reportes.stream()
                .map(this::toReporteDTO)
                .collect(Collectors.toList());
    }

    default List<Reporte> toReporteList(List<ReporteDTO> reporteDTOs) {
        return reporteDTOs.stream()
                .map(this::toReporte)
                .collect(Collectors.toList());
    }

    ReporteDTO toReporteDTO(Reporte reporte);

    Reporte toReporte(ReporteDTO reporteDTO);

    default UsuarioDTO toDTOWithReportes(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = toDTO(usuario);
        usuarioDTO.setReportes(toReporteDTOList((List<Reporte>) usuario.getReportes())); // Llama al método que mapea la lista de Reportes
        return usuarioDTO;
    }

}
