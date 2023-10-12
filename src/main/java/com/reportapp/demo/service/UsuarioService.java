package com.reportapp.demo.service;

import com.reportapp.demo.model.Usuario;

public interface UsuarioService {

    boolean registrar(Usuario usuario);

    boolean verificarExistencia(Usuario usuario);

    Usuario loguin(String email, String password);

    boolean verificarIdentificacion(String identificacion);

}
