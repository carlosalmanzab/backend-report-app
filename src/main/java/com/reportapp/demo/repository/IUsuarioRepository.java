package com.reportapp.demo.repository;

import com.reportapp.demo.entity.Reporte;
import com.reportapp.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
    Optional<Boolean> existsByEmailAndPassword(String email, String password);
    Optional<Boolean> existsByIdentificacion(String email);

}
