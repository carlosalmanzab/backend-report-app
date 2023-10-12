package com.reportapp.demo.repository;

import com.reportapp.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndPassword(String email, String password);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String password);

    Boolean existsByIdentificacion(String email);
}
