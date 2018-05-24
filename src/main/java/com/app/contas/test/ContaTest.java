package com.app.contas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.contas.entity.ContaEntity;
import com.app.contas.repository.ContaRepository;

public class ContaTest {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Test
	public void testeInjecao() {
		
		List<ContaEntity> listContas = contaRepository.findAll();
		
		assertNull(listContas);
		
	}
	

}
