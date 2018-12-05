package com.app.contas.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.contas.entity.ContaEntity;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

	List<ContaEntity> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

}
