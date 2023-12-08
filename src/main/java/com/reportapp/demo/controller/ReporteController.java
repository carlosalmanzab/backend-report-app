package com.reportapp.demo.controller;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.service.JwtService;
import com.reportapp.demo.service.ReporteService;
import com.reportapp.demo.service.SubscriberMessagingService;
import com.reportapp.demo.share.constant.CustomMessage;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reporte", description = "Endpoint para el manejo de reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SubscriberMessagingService subscriberMessagingService;

    @GetMapping()
    public ResponseEntity<List<ReporteDTO>> listar() {
        return reporteService.listarReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> reportePorId(@PathVariable Long id) {
        return reporteService.reportePorId(id);
    }

    @PostMapping()
    public ResponseEntity<ReporteDTO> guardar(@RequestBody ReporteDTOSave reporteDTOSave, HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        String username = jwtService.getUsernameFromToken(token);
        ResponseEntity<ReporteDTO> response = reporteService.guardarReporte(reporteDTOSave, username);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            simpMessagingTemplate.convertAndSend("/topic/reportes", response.getBody());

            ReporteDTO responseBody = response.getBody();
            if (responseBody != null && responseBody.getTipoReporte() != null && responseBody.getTipoReporte().getDescripcion() != null
                    && responseBody.getDescripcion() != null) {
                subscriberMessagingService.communicate(responseBody.getTipoReporte().getDescripcion(),
                        responseBody.getDescripcion(), CustomMessage.NEW_REPORT_TOPIC);
            }
            
        }
        return response;
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<ReporteDTO>> reportesPorUsuario(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        String username = jwtService.getUsernameFromToken(token);
        return reporteService.reportesPorUsuario(username);
    }

}
