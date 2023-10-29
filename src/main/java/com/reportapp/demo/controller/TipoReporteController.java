package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTO;
import com.reportapp.demo.entity.dto.tipoReporte.TipoReporteDTOSave;
import com.reportapp.demo.service.TipoReporteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tiporeportes")
@Tag(name = "Tipo Reporte", description = "Endpoint para el manejo de los Tipos de Reportes")
public class TipoReporteController {
    @Autowired
    private TipoReporteService tipoReporteService;

    @GetMapping()
    public ResponseEntity<List<TipoReporteDTO>> listar() {
        return tipoReporteService.listarTiposDeIndentificaciones();
    }

    @PostMapping()
    public ResponseEntity<TipoReporteDTO> guardar(@Valid @RequestBody TipoReporteDTOSave tipoReporteDTOSave) {
        return tipoReporteService.guardar(tipoReporteDTOSave);
    }
}
