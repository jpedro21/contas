package com.app.contas.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractContaValidation implements Validator {
	
	@Autowired
	private Validator javaxValidator;

	@Override
	public void validate(Object target, Errors errors) {
		javaxValidator.validate(target, errors);
		
		businessValidation(target);
	}
	
//	public validate(Object target) {
//		validate(target)
//	}
	
	public abstract void businessValidation(Object target);

}
