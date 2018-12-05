package com.app.contas.business;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.app.contas.dto.ContaDto;
import com.app.contas.entity.ContaEntity;
import com.app.contas.repository.ContaRepository;
import com.app.contas.service.exception.ContaNaoEncontrada;

@Component
public class ContaBusiness {
	
	@Autowired
	private ContaRepository contaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Long criar(ContaDto conta) {
		
		ContaEntity contaEntity = modelMapper.map(conta, ContaEntity.class);
		return contaRepository.save(contaEntity).getId();
	}
	
	public List<ContaDto> buscarContasPorData(String data) {
		
		LocalDate dataInicio = LocalDate.parse(data, DateTimeFormat.forPattern("MM-yyyy"));
		LocalDate dataFim = dataInicio.dayOfMonth().withMaximumValue();
		
		return mapearListaDto(contaRepository.findByDataBetween(dataInicio, dataFim));
	}
	
	public ContaDto buscarPorId(Long id) {
		
		ContaEntity conta = contaRepository.findOne(id);
		return modelMapper.map(conta, ContaDto.class);
	}


	public void alterarConta(ContaDto conta) {
		
		ContaEntity entity = modelMapper.map(conta, ContaEntity.class);
		contaRepository.save(entity);
	}
	
	public void deletarConta(Long idConta) {
		
		try {
			contaRepository.delete(idConta);		
		} catch(EmptyResultDataAccessException e) {
			
			throw new ContaNaoEncontrada("Conta não encontrada");
		}
	}
	
	public List<ContaDto> buscarTodasContas() {
		
		return mapearListaDto(contaRepository.findAll());
	}
	
	private List<ContaDto> mapearListaDto(List<ContaEntity> conta) {
			
		return conta.stream()
				.map(c -> modelMapper.map(c, ContaDto.class))
				.collect(Collectors.toList());
	}




}
