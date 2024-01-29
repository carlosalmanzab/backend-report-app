package com.reportapp.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reportapp.demo.entity.dto.reporte.ReporteDTOSave;

@CrossOrigin
@Controller
@RequestMapping("/ws")
public class ReporteWebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ReporteWebSocketController(SimpMessagingTemplate template) {
        this.simpMessagingTemplate = template;
    }

    @MessageMapping("/report-created")
    public void onReportCreated(ReporteDTOSave reporteDTOSave) {
        simpMessagingTemplate.convertAndSend("/topic/reportes", reporteDTOSave);
    }
}
