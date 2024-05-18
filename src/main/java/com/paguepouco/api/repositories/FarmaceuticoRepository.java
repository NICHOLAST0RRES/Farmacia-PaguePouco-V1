package com.paguepouco.api.repositories;

import com.paguepouco.api.model.Farmaceutico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico, Long> {
    Page<Farmaceutico> findAllByAtivoTrue(Pageable paginacao);
}
