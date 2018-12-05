package com.app.contas.test;

import java.math.BigDecimal;
import java.util.function.Consumer;

import org.joda.time.LocalDate;

import com.app.contas.dto.ContaDto;

public class ContaBuilder {

	public Long id;
	public String descricao;
	public LocalDate data;
	public BigDecimal valor;
	
	public ContaBuilder atributos(Consumer<ContaBuilder> consumer) {
		consumer.accept(this);
		return this;
	}
	
	public ContaDto build() {
		
		return new ContaDto(id, descricao, data, valor);
	}
}
