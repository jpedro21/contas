package com.app.contas.dto;

public class ErroDto {
	
	private String msg;
	private Long timestamp;
	private Integer status;
	
	public ErroDto(String msg, Long timestamp, Integer status) {
		super();
		this.msg = msg;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	
}
