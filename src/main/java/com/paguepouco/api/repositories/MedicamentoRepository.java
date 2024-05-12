package com.paguepouco.api.repositories;


import com.paguepouco.api.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Page<Medicamento> findAllByAtivoTrue(Pageable paginacao);
}
