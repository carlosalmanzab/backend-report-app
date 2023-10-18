package com.reportapp.demo.repository;

import com.reportapp.demo.entity.Barrio;
import com.reportapp.demo.entity.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBarrioRepository extends JpaRepository<Barrio, Long> {
    List<Barrio> findByComuna(Comuna comuna);
}
