package com.reportapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reportapp.demo.entity.Imagen;

public interface IImagenRepository extends JpaRepository<Imagen, Long> {
    
}
