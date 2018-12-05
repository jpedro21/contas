package com.app.contas.handler;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.contas.dto.ErroDto;
import com.app.contas.message.MessageByLocaleService;
import com.app.contas.service.exception.ContaNaoEncontrada;
import com.app.contas.service.exception.ParametroInvalidoException;
import com.app.contas.utils.ErroDtoBuilder;

@RestControllerAdvice
public class ResourceHandlerException {
	
	@Autowired
	private ErroDtoBuilder erroBuilder;
	
	@ExceptionHandler(ContaNaoEncontrada.class)
	public ResponseEntity<List<ErroDto>> handleContaNaoEncontrada(ContaNaoEncontrada e) {
		
		List<ErroDto> erro = erroBuilder.constroiErroDto(e, HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<ErroDto>> handleContaCamposRequiridos(ConstraintViolationException e) {
		
		List<ErroDto> erro = erroBuilder.constroiErroDto(e, HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(ParametroInvalidoException.class)
	public ResponseEntity<List<ErroDto>> handleContaParametroInvalido(ParametroInvalidoException e) {
		
		List<ErroDto> erro = erroBuilder.constroiErroDto(e, HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
