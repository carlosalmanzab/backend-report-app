package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.mapper.ReporteMapper;
import com.reportapp.demo.entity.mapper.UsuarioMapper;
import com.reportapp.demo.share.constant.UsuarioMessageConstants;
import com.reportapp.demo.share.dto.ResponseMessage;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.entity.Usuario;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.UsuarioService;
import com.reportapp.demo.util.PasswordEncoder;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;

    private ReporteMapper reporteMapper;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, ReporteMapper reporteMapper) {
        this.usuarioMapper = usuarioMapper;
        this.reporteMapper = reporteMapper;
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        ResponseMessage.ResponseMessageBuilder responseMessage =  ResponseMessage.builder();
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NO_FOUND)
                    .build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario.get());
        List<ReporteDTO> reporteDTOList = usuarioDTO.getReportes().stream().toList();

        usuarioDTO.setReportes(reporteDTOList);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> registrar(UsuarioDTOSave usuarioDTOSave) {
        ResponseMessage.ResponseMessageBuilder responseMessage =  ResponseMessage.builder();

        if (usuarioDTOSave == null) {
            responseMessage
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NULL)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTOSave);
        usuario.setPassword(PasswordEncoder.encode(usuarioDTOSave.getPassword()));
        Usuario usuarioSave = usuarioRepository.save(usuario);

        if (usuarioSave == null) {
            responseMessage
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_SAVE)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }

        responseMessage
                .code(HttpStatus.CREATED.value())
                .message(UsuarioMessageConstants.USUARIO_SUCCESS_SAVE)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @Override
    public ResponseEntity<?> login(UsuarioDTOLogin usuarioDTOLogin) {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();

        if (usuarioDTOLogin == null) {
            responseMessage
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NULL)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        Optional<Usuario> usuario = usuarioRepository.findByEmailAndPassword(usuarioDTOLogin.getEmail(), usuarioDTOLogin.getEmail());

        if (usuario.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NO_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }

    @Override
    public ResponseEntity<?> reportesPorId(Long id) {
        ResponseMessage.ResponseMessageBuilder responseMessage = ResponseMessage.builder();
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        List<Reporte> reportes = usuario.get().getReportes();
        if (usuario.isEmpty() || reportes.isEmpty()) {
            responseMessage
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(UsuarioMessageConstants.USUARIO_ERROR_NO_FOUND_REPORTES)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        List<ReporteDTO> reporteDTO = reportes.stream().map(reporteMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(reporteDTO);
    }

/*
    @Override
    public boolean verificarExistencia(Usuario usuario) throws UsuarioNotFoundException {
        return usuarioRepository.existsByEmail(usuario.getEmail());
    }

    @Override
    public Usuario loguin(String email, String password) throws UsuarioNotFoundException {
        boolean exists = usuarioRepository.existsByEmailAndPassword(email, password);

        if (exists) {
            return usuarioRepository.findByEmailAndPassword(email, password);
        } else {
            return null;
        }

    }

    @Override
    public boolean verificarIdentificacion(String identificacion) throws UsuarioNotFoundException {
        return usuarioRepository.existsByIdentificacion(identificacion) != null;
    }
*/
}
