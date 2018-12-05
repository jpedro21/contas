package com.app.contas.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contas.business.ContaCreditoBusiness;
import com.app.contas.dto.ContaCreditoDto;

@Service
public class ContaCreditoService {
	
	@Autowired
	private ContaCreditoBusiness contaCreditoBusiness;

	public Long criar(@Valid ContaCreditoDto contaCreditoDto) {
		
		return contaCreditoBusiness.criar(contaCreditoDto);
	}

}
