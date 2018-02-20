package com.app.contas.business;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
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
	
	public ContaDto buscarPorId(Long id) {
		
		ContaEntity conta = contaRepository.findOne(id);
		return modelMapper.map(conta, ContaDto.class);
	}

	public List<ContaDto> buscarPorData(LocalDate data) {
		
		List<ContaEntity> conta =  contaRepository.findByDataInicioAfterAndDataFimBefore(data, data);
		conta.stream().forEach(c -> System.out.println("Conta com ID: " + c.getId()));
		List<ContaDto> contaDto = mapearListaDto(conta);
		
		return contaDto;
	}
	
	public List<ContaDto> buscarContasProximoMes(LocalDate mes) {
		
		List<ContaEntity> contaEntity = contaRepository.findByDataInicioAfterAndDataFimBefore(mes, mes.plusMonths(1));
		return mapearListaDto(contaEntity);
	}

	public ContaEntity criarConta(ContaDto conta) {
		
		LocalDate dataFim = new LocalDate();
		
		if(conta.getContaCredito() != null) {
			
			Integer qtdParcela = conta.getContaCredito().getQtdParcelas();		
			dataFim = conta.getDataInicio().plusMonths(qtdParcela);
		
		} else {
			dataFim = conta.getDataInicio();
		}
		
		conta.setDataFim(dataFim);
		
		ContaEntity contaEntity = modelMapper.map(conta, ContaEntity.class);
		return contaRepository.save(contaEntity);
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
	
	private List<ContaDto> mapearListaDto(List<ContaEntity> conta) {
			
		return conta.stream()
				.map(c -> modelMapper.map(c, ContaDto.class))
				.collect(Collectors.toList());
	}

}
