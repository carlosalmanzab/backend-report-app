package com.reportapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reportapp.demo.entity.Coordenadas;

public interface ICoordenadasRepository extends JpaRepository<Coordenadas, Long> {
    
}
