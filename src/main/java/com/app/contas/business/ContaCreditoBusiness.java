package com.app.contas.business;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.contas.dto.ContaCreditoDto;
import com.app.contas.entity.ContaCreditoEntity;
import com.app.contas.repository.ContaCreditoRepository;

@Component
public class ContaCreditoBusiness {

	@Autowired
	private ContaCreditoRepository contaCreditoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Long criar(ContaCreditoDto conta) {
		
		ContaCreditoEntity contaCreditoEntity = modelMapper.map(conta, ContaCreditoEntity.class);
		
		return contaCreditoRepository.save(contaCreditoEntity).getId();
	}

}
