package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?> buscarPorId(Long id);
    ResponseEntity<?> registrar(UsuarioDTOSave usuarioDTOSave);
    ResponseEntity<?> login(UsuarioDTOLogin usuarioDTOLogin);
    ResponseEntity<?> reportesPorId(Long id);
/*
    ResponseMessage verificarExistencia(Usuario usuario) throws UsuarioNotFoundException;

    UsuarioDTO loguin();

    ResponseMessage verificarIdentificacion(String identificacion) throws UsuarioNotFoundException;
*/
}
