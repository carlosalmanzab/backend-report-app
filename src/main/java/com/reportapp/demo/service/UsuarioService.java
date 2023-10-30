package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;

import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<UsuarioDTO> buscar(String  username);
}
