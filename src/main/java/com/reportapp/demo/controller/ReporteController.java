package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.service.ReporteService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reporte", description = "Endpoint para el manejo de reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @GetMapping()
    public ResponseEntity<List<ReporteDTO>> listar() {
        return reporteService.listarReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> reportePorId(@PathVariable Long id) {
        return reporteService.reportePorId(id);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<ReporteDTO> guardar(@RequestBody ReporteDTOSave reporteDTOSave, @PathVariable Long idUsuario) {
        return reporteService.guardarReporte(reporteDTOSave, idUsuario);
    }

}
