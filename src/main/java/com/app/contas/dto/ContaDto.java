package com.app.contas.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaDto {
	
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	private String descricao;
	
	@NotNull(message = "CT-2")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("data")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	@Min(value = 1, message = "CT-3")
	@NotNull(message = "CT-4")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	public ContaDto() {}
	
	public ContaDto(Long id, String descricao, LocalDate data, BigDecimal valor) {
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}