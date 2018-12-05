package com.app.contas.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.contas.dto.ContaCreditoDto;
import com.app.contas.service.ContaCreditoService;

@RestController
@RequestMapping("/conta-credito")
public class ContaCreditoController {
	
	@Autowired
	private ContaCreditoService contaCreditoService;
	
	@PostMapping
	public ResponseEntity<Void> cadastrarContaCredito(@RequestBody ContaCreditoDto contaCreditoDto) {
		
		Long idCriado = contaCreditoService.criar(contaCreditoDto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(idCriado)
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
