package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;
import org.springframework.http.ResponseEntity;

public interface TipoReporteService {
    ResponseEntity<?> listarTiposDeIndentificaciones();
    ResponseEntity<?> guardar(TipoReporteDTOSave tipoReporteDTOSave);
}
