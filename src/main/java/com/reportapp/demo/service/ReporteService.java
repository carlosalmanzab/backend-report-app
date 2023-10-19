package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import org.springframework.http.ResponseEntity;

public interface ReporteService {

    ResponseEntity<?> listarReportes();
    ResponseEntity<?> reportePorId(Long id);
    ResponseEntity<?> guardarReporte(ReporteDTOSave reporteDTOSave);
}
