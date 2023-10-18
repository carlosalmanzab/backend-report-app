package com.reportapp.demo.repository;

import com.reportapp.demo.entity.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComunaRepository extends JpaRepository<Comuna, Long> {
}
