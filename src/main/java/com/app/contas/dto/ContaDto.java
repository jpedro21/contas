package com.app.contas.dto;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaDto {
	
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("data")
	private LocalDate dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;
	
	private BigDecimal valor;
	
	@JsonInclude(Include.NON_NULL)
	private ContaCreditoDto contaCredito;
	
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
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDate data) {
		this.dataInicio = data;
	}
	
	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public ContaCreditoDto getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(ContaCreditoDto contaCredito) {
		this.contaCredito = contaCredito;
	}
}