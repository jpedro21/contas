package com.app.contas.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.contas.dto.ContaDto;

public interface ContaApi {
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> cadastrar(@RequestBody ContaDto contaDto);
}
