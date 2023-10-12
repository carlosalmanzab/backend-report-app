package com.reportapp.demo.service.serviceImpl;

import com.reportapp.demo.model.Usuario;
import com.reportapp.demo.repository.IUsuarioRepository;
import com.reportapp.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public boolean registrar(Usuario usuario) {
        return usuarioRepository.save(usuario) != null;
    }

    @Override
    public boolean verificarExistencia(Usuario usuario) {
        return usuarioRepository.existsByEmail(usuario.getEmail());
    }

    @Override
    public Usuario loguin(String email, String password) {
        boolean exists = usuarioRepository.existsByEmailAndPassword(email, password);

        if (exists) {
            return usuarioRepository.findByEmailAndPassword(email, password);
        } else {
            return null;
        }

    }

    @Override
    public boolean verificarIdentificacion(String identificacion) {
        return usuarioRepository.existsByIdentificacion(identificacion) != null;
    }

}
