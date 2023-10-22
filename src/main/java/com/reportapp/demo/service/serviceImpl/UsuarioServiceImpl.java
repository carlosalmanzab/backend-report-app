package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.dto.reporte.ReporteDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOLogin;
import com.reportapp.demo.entity.mapper.ReporteMapper;
import com.reportapp.demo.entity.mapper.UsuarioMapper;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTO;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.entity.Usuario;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.UsuarioService;
import com.reportapp.demo.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<UsuarioDTO> buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario.get());
        List<ReporteDTO> reporteDTOList = usuarioDTO.getReportes().stream().toList();

        usuarioDTO.setReportes(reporteDTOList);
        return ResponseEntity.ok(usuarioDTO);
    }


    @Override
    public ResponseEntity<UsuarioDTO> registrar(UsuarioDTOSave usuarioDTOSave) {

        if (usuarioDTOSave == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTOSave);
        usuario.setPassword(PasswordEncoder.encode(usuarioDTOSave.getPassword()));
        Usuario usuarioSave = usuarioRepository.save(usuario);

        if (usuarioSave == null) return ResponseEntity.internalServerError().build();

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("usuarios/{id}");
        URI location = builder.buildAndExpand(usuarioSave.getId()).toUri();

        return ResponseEntity.created(location).body(usuarioMapper.toDTO(usuarioSave));
    }

    @Override
    public ResponseEntity<UsuarioDTO> login(UsuarioDTOLogin usuarioDTOLogin) {
        if (usuarioDTOLogin == null) return ResponseEntity.badRequest().build();

        Optional<Usuario> usuario = usuarioRepository
        .findByEmailAndPassword(usuarioDTOLogin.getEmail(), usuarioDTOLogin.getEmail());

        if (usuario.isEmpty()) return ResponseEntity.notFound().build();

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario.get());
        return ResponseEntity.ok(usuarioDTO);
    }

    @Override
    public ResponseEntity<List<ReporteDTO>> reportesPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        Optional<List<Reporte>> reportes = usuario.map(Usuario::getReportes);
        
        if (usuario.isEmpty() || reportes.isEmpty()) return ResponseEntity.notFound().build();
        
        List<ReporteDTO> reporteDTO = reportes.get().stream().map(reporteMapper::toDTO).toList();
        return ResponseEntity.ok(reporteDTO);
    }

    @Override
    public ResponseEntity<Boolean> verificarExistencia(UsuarioDTOSave usuarioDTOSave) {
        Boolean hasUsuario = usuarioRepository.existsByEmail(usuarioDTOSave.getEmail());

        if (Boolean.FALSE.equals(hasUsuario)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }
/*

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
