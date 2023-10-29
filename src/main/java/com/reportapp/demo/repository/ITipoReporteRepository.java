package com.reportapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reportapp.demo.entity.TipoReporte;

import java.util.List;

@Repository
public interface ITipoReporteRepository extends JpaRepository<TipoReporte, Long> {
    List<TipoReporte> findAllByEstado(boolean estado);

}
