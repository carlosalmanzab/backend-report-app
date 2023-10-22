package com.reportapp.demo.service;

import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<UsuarioDTO> buscarPorId(Long id);
    ResponseEntity<UsuarioDTO> registrar(UsuarioDTOSave usuarioDTOSave);
    ResponseEntity<UsuarioDTO> login(UsuarioDTOLogin usuarioDTOLogin);
    ResponseEntity<List<ReporteDTO>> reportesPorId(Long id);
    ResponseEntity<Boolean> verificarExistencia(UsuarioDTOSave usuarioDTOSave);
/*
    ResponseMessage verificarExistencia(Usuario usuario) throws UsuarioNotFoundException;

    UsuarioDTO loguin();

    ResponseMessage verificarIdentificacion(String identificacion) throws UsuarioNotFoundException;
*/
}
