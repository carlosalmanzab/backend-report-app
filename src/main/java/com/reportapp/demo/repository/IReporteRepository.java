package com.reportapp.demo.repository;

import com.reportapp.demo.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReporteRepository extends JpaRepository<Reporte, Long> {
    Reporte save(Reporte reporte);


}
