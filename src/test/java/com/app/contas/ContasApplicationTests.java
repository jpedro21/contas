package com.app.contas;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.contas.entity.ContaCreditoEntity;
import com.app.contas.entity.ContaEntity;
import com.app.contas.repository.ContaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContasApplicationTests {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Before
	public void setup() {
		contaRepository.save(new ContaEntity(
				"CONTA DEBITO 1",	
				new LocalDate(2018, 1, 1), 
				new LocalDate(2018, 1, 1), 
				new BigDecimal(2000.9)));
		
		contaRepository.save(new ContaEntity(
				"CONTA DEBITO 2",	
				new LocalDate(2018, 2, 15), 
				new LocalDate(2018, 2, 15), 
				new BigDecimal(100.5)));
		
		contaRepository.save(new ContaEntity(
				"CONTA CREDITO 1",	
				new LocalDate(2018, 1, 1), 
				new LocalDate(2018, 5, 1), 
				new BigDecimal(250.5),
				new ContaCreditoEntity(5)));
		
		contaRepository.save(new ContaEntity(
				"CONTA CREDITO 2",	
				new LocalDate(2018, 1, 15), 
				new LocalDate(2018, 2, 15), 
				new BigDecimal(100.5),
				new ContaCreditoEntity(2)));
	}
	
	@Test
	public void testException() {
		
		contaRepository.save(new ContaEntity(
				"CONTA DEBITO 1",	
				null, 
				null, 
				null));
		
	}

}
