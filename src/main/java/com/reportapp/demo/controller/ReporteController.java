package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.service.ReporteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporte")
@Tag(name = "Reporte", description = "Endpoint para el manejo de reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @GetMapping()
    public ResponseEntity<?> listar() {
        return reporteService.listarReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> reportePorId(@PathVariable Long id) {
        return reporteService.reportePorId(id);
    }

    @PostMapping()
    public ResponseEntity<?> guardar(@RequestBody ReporteDTOSave reporteDTOSave) {
        return reporteService.guardarReporte(reporteDTOSave);
    }

}
