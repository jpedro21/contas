package com.app.contas.controller;

import java.net.URI;
import java.util.List;


import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.contas.service.ContaService;
import com.app.contas.dto.ContaDto;
import com.app.contas.entity.ContaEntity;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Void> bucarPorId(@PathVariable Long id) {
		
		contaService.buscarPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/{data}")
	public ResponseEntity<List<ContaDto>> buscarPorData(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data) {
		
		List<ContaDto> listConta = contaService.buscarPorData(data); 	 
		
		return ResponseEntity.status(HttpStatus.OK).body(listConta);
	}
	
	@GetMapping("/proximo-mes/{mes}")
	public ResponseEntity<List<ContaDto>> buscarContasProximoMes(@PathVariable @DateTimeFormat(pattern="MM") LocalDate mes) {
		
		List<ContaDto> listConta = contaService.contasProximoMes(mes);
		
		return ResponseEntity.status(HttpStatus.OK).body(listConta);
	}
	
	@PostMapping
	public ResponseEntity<Void> cadastrar(@RequestBody ContaDto contaDto) {
		
		ContaEntity entity = contaService.criar(contaDto);		
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(entity.getId())
				.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterarConta(@PathVariable Long idConta, @RequestBody ContaDto conta) {
		
		contaService.alterarConta(idConta, conta);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletarConta(@PathVariable Long idConta) {
		
		contaService.deletarConta(idConta);
		
		return ResponseEntity.noContent().build();
	}
}
