package com.app.contas.dto;

public class ErroDto {
	
	private String codErro;
	private String msg;
	private Long timestamp;
	private Integer status;
	
	public ErroDto(String codErro, String msg, Long timestamp, Integer status) {
		
		this.codErro = codErro;
		this.msg = msg;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public String getCodErro() {
		return codErro;
	}

	public void setCodErro(String codErro) {
		this.codErro = codErro;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
