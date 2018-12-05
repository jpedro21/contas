package com.app.contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.contas.entity.ContaCreditoEntity;

public interface ContaCreditoRepository extends JpaRepository<ContaCreditoEntity, Long> {

}
