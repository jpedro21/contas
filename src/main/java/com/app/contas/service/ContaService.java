package com.app.contas.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.contas.business.ContaBusiness;
import com.app.contas.dto.ContaDto;
import com.app.contas.entity.ContaEntity;
import com.app.contas.service.exception.ContaNaoEncontrada;

@Service
public class ContaService {
	
	@Autowired
	private ContaBusiness contaBusiness;
	
	public ContaDto buscarPorId(Long id) {
		
		ContaDto conta = contaBusiness.buscarPorId(id);
		
		if(conta == null) {
			throw new ContaNaoEncontrada("Conta não encontrada.");
		}
		
		return contaBusiness.buscarPorId(id);
	}
	
	public List<ContaDto> buscarPorData(LocalDate data) {
		
		List<ContaDto> listConta = contaBusiness.buscarPorData(data);
		
		if(listConta.isEmpty()) {
			throw new ContaNaoEncontrada("Conta não encontrada.");
		}
		
		return listConta;
	}
	
	public List<ContaDto> contasProximoMes(LocalDate mes) {
		
		this.buscarPorData(mes);
		
		return contaBusiness.buscarContasProximoMes(mes);
	}

	public ContaEntity criar(ContaDto contaDto) {
		
		return contaBusiness.criarConta(contaDto);
	}

	public void alterarConta(Long idConta, ContaDto conta) {
		
		this.buscarPorId(idConta);
		
		contaBusiness.alterarConta(conta);
	}

	public void deletarConta(Long idConta) {
		
		this.buscarPorId(idConta);
		
		contaBusiness.deletarConta(idConta);		
	}

}
