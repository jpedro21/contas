package com.app.contas.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataDto<T> {
	
	@JsonFormat()
	List<T> data;

	public DataDto(List<T> todasContas) {
		this.data = todasContas;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
