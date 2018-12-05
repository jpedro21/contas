package com.app.contas.service.exception;

public class ContaNaoEncontrada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContaNaoEncontrada(String msg) {
		super(msg);
	}
	
	public ContaNaoEncontrada(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
