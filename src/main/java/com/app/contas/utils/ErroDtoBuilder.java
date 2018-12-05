package com.app.contas.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.contas.dto.ErroDto;
import com.app.contas.message.MessageByLocaleService;

@Component
public class ErroDtoBuilder {
	
	@Autowired
	private MessageByLocaleService message;
	
	public List<ErroDto> constroiErroDto(RuntimeException exception, HttpStatus code) {
		
		return Arrays.asList(new ErroDto(exception.getMessage(), 
				   						 message.getMessage(exception.getMessage()), 
				   						 System.currentTimeMillis(), 
				   						 code.value()));
	}
	
	public List<ErroDto> constroiErroDto(ConstraintViolationException exception, HttpStatus code) {
		
		List<ErroDto> erros = new ArrayList<>();
		
		exception.getConstraintViolations()
			.stream()
			.forEach(e -> erros.add(new ErroDto(e.getMessage(), 
						 				message.getMessage(e.getMessage()), 
						 				System.currentTimeMillis(), 
						 				code.value())));
		
		return erros;
	}

}
