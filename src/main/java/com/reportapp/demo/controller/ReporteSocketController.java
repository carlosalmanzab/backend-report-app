package com.reportapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;
import com.reportapp.demo.service.JwtService;
import com.reportapp.demo.service.ReporteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReporteSocketController {
    
    @Autowired
    private ReporteService reporteService;

    @Autowired
    private JwtService jwtService;

    @MessageMapping("/reporte")
    @SendTo("/topic/reporte")
    public ResponseEntity<ReporteDTO> guardarReporteSocket(ReporteDTOSave reporteDTOSave, HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        String username = jwtService.getUsernameFromToken(token);
        return reporteService.guardarReporte(reporteDTOSave, username);
    }
}
