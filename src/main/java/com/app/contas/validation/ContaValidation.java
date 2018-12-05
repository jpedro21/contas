package com.app.contas.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.app.contas.dto.ContaDto;

public class ContaValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ContaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ContaDto conta = (ContaDto) target;
		if(conta.getId() != null) {
			
			errors.rejectValue("id", "CT-1");
		}
		
	}

}
