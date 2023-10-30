package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ReporteService {

    ResponseEntity<List<ReporteDTO>> listarReportes();
    ResponseEntity<ReporteDTO> reportePorId(Long id);
    ResponseEntity<ReporteDTO> guardarReporte(ReporteDTOSave reporteDTOSave, String username);
    ResponseEntity<List<ReporteDTO>> reportesPorUsuario(String username);
}
