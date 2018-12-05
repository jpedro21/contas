package com.app.contas.controller;

import java.net.URI;
import java.util.List;

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

import com.app.contas.dto.ContaDto;
import com.app.contas.dto.DataDto;
import com.app.contas.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController implements ContaApi {
	
	@Autowired
	private ContaService contaService;
		
	public ResponseEntity<Void> cadastrar(@RequestBody ContaDto contaDto) {
		
		Long idCriado = contaService.criar(contaDto);		
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(idCriado)
				.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/data/{data}")
	public ResponseEntity<DataDto<ContaDto>> bucarPorData(
			@PathVariable @DateTimeFormat(pattern = "MM-yyyy") String data) {
		
		List<ContaDto> todasContas = contaService.bucarPorData(data);
		
		DataDto<ContaDto> retorno = new DataDto<>(todasContas); 
		
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}
	
	@GetMapping
	public ResponseEntity<List<ContaDto>> bucarTodasContas() {
		
		List<ContaDto> todasContas = contaService.bucarTodasContas();
		
		return ResponseEntity.status(HttpStatus.OK).body(todasContas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Void> bucarPorId(@PathVariable Long id) {
		
		contaService.buscarPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
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
