package com.paguepouco.api.repositories;


import com.paguepouco.api.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
}
