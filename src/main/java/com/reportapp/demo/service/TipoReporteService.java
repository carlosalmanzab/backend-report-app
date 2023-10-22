package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTO;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TipoReporteService {
    ResponseEntity<List<TipoReporteDTO>> listarTiposDeIndentificaciones();
    ResponseEntity<TipoReporteDTO> guardar(TipoReporteDTOSave tipoReporteDTOSave);
}
