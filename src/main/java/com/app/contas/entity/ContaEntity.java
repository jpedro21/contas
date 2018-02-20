package com.app.contas.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "conta")
public class ContaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@NotNull(message = "A data não pode ser nula.")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataInicio;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataFim;
	
	@Min(value = 1, message = "O valor deve ser maior que 1.")
	@NotNull(message = "O valor não pode ser nula.")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_credito_id")
	private ContaCreditoEntity contaCredito;
	
	public ContaEntity(String descricao, LocalDate dataInicio, LocalDate dataFim, BigDecimal valor) {
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valor = valor;
	}

	public ContaEntity(String descricao, LocalDate dataInicio, LocalDate dataFim, BigDecimal valor,
			ContaCreditoEntity contaCredito) {
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valor = valor;
		this.contaCredito = contaCredito;
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
	
	public ContaCreditoEntity getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(ContaCreditoEntity contaCredito) {
		this.contaCredito = contaCredito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaEntity other = (ContaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
