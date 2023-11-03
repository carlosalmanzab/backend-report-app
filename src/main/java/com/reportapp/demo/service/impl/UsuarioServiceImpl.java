package com.reportapp.demo.service.impl;

import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.mapper.UsuarioMapper;
import com.reportapp.demo.entity.usuario.Usuario;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;



    public UsuarioServiceImpl(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public ResponseEntity<UsuarioDTO> buscar(String username) {
        
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario.get());
        List<ReporteDTO> reporteDTOList = usuarioDTO.getReportes().stream().toList();

        usuarioDTO.setReportes(reporteDTOList);
        return ResponseEntity.ok(usuarioDTO);
    }

}
