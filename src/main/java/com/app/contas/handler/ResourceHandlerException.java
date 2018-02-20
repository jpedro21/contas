package com.app.contas.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.contas.dto.ErroDto;
import com.app.contas.service.exception.ContaNaoEncontrada;

@RestControllerAdvice
public class ResourceHandlerException {
	
	@ExceptionHandler(ContaNaoEncontrada.class)
	public ResponseEntity<ErroDto> handleContaNaoEncontrada(ContaNaoEncontrada e) {
		
		ErroDto erro = new ErroDto(e.getMessage(), System.currentTimeMillis(), 404);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public  ResponseEntity<ErroDto> handleConstraintViolationException(ConstraintViolationException e) {
 		
		ErroDto erro = new ErroDto(e.getMessage(), System.currentTimeMillis(), 404);
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
