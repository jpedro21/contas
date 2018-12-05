package com.app.contas.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.app.contas.business.ContaBusiness;
import com.app.contas.dto.ContaDto;
import com.app.contas.service.exception.ContaNaoEncontrada;
import com.app.contas.service.exception.ParametroInvalidoException;

@Service
@Validated
public class ContaService {
	
	@Autowired
	private ContaBusiness contaBusiness;
	
	public Long criar(@Valid ContaDto contaDto) {
		
		if(contaDto.getId() != null) {
			throw new ParametroInvalidoException("CT-1");
		}
		
		return contaBusiness.criar(contaDto);
	}
	
	public List<ContaDto> bucarPorData(String data) {
		
		return contaBusiness.buscarContasPorData(data);
	}
	
	public ContaDto buscarPorId(Long id) {
		
		ContaDto conta = contaBusiness.buscarPorId(id);
		
		if(conta == null) {
			throw new ContaNaoEncontrada("Conta não encontrada.");
		}
		
		return contaBusiness.buscarPorId(id);
	}


	public void alterarConta(Long idConta, ContaDto conta) {
		
		this.buscarPorId(idConta);
		
		contaBusiness.alterarConta(conta);
	}

	public void deletarConta(Long idConta) {
		
		this.buscarPorId(idConta);
		
		contaBusiness.deletarConta(idConta);		
	}

	public List<ContaDto> bucarTodasContas() {
		
		return contaBusiness.buscarTodasContas();
	}



}
