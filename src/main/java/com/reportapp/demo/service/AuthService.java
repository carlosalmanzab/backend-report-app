package com.reportapp.demo.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.dto.usuario.UsuarioDTOSave;
import com.reportapp.demo.entity.mapper.UsuarioMapper;
import com.reportapp.demo.entity.usuario.Role;
import com.reportapp.demo.entity.usuario.Usuario;
import com.reportapp.demo.jwt.AuthResponse;
import com.reportapp.demo.jwt.LoginRequest;
import com.reportapp.demo.repository.IBarrioRepository;
import com.reportapp.demo.repository.IUsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IBarrioRepository barrioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<AuthResponse> registrar(UsuarioDTOSave usuarioDTOSave) {
        if (usuarioDTOSave == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTOSave);
        usuario.setPassword(passwordEncoder.encode(usuarioDTOSave.getPassword()));
        Optional<Barrio> barrioOptional = barrioRepository.findById(usuarioDTOSave.getBarrioId());

        barrioOptional.ifPresent(usuario::setBarrio);
        usuario.setRole(Role.USER);

        Usuario usuarioSave = usuarioRepository.save(usuario);

        if (usuarioSave.getId() == null)
            return ResponseEntity.internalServerError().build();

        AuthResponse authResponse = AuthResponse
                .builder()
                .token(jwtService.getToken(usuario))
                .build();

        return ResponseEntity.ok().body(authResponse);
    }

    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        UserDetails usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        AuthResponse authResponse = AuthResponse
                .builder()
                .token(jwtService.getToken(usuario))
                .build();
        return ResponseEntity.ok().body(authResponse);
    }
}
