package com.reportapp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reportapp.demo.service.JwtService;
import com.reportapp.demo.service.SubscriberMessagingService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/sub/messaging")
@Tag(name = "subscriber", description = "Endpoint para el manejo de las subscripciones a mensajes")
public class SubscriberMessagingController {

    @Autowired
    private SubscriberMessagingService subscriberMessagingService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping()
    public ResponseEntity subscribir(@RequestBody String token, HttpServletRequest request) {
        String jwtToken = jwtService.getTokenFromRequest(request);
        String username = jwtService.getUsernameFromToken(jwtToken);
        return subscriberMessagingService.save(token, username);
        
    }
}
