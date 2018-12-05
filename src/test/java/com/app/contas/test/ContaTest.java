package com.app.contas.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyString;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.contas.entity.ContaEntity;
import com.app.contas.repository.ContaRepository;

import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaTest {

	@LocalServerPort
	private int port;
	
	private static String URI = "conta";
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Test
	public void testeCadastroConta() {
		
		given()
			.port(port)
			.contentType(ContentType.JSON)
			.body(new ContaBuilder().atributos(b -> {
				b.descricao = anyString();
				b.valor = new BigDecimal(100L);
				b.data = LocalDate.now();
			}).build())
		.when()
			.post(URI)
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void testeCadastroContaSemData() {
		
		given()
			.port(port)
			.contentType(ContentType.JSON)
			.body(new ContaBuilder()
						.atributos(b -> {
							b.descricao = anyString();
							b.valor = new BigDecimal(100L);
						}).build())
		.when()
			.post(URI)
		.then()
			.body("codErro[0]", equalTo("CT-2"))
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testeCadastroContaSemValor() {
		
		given()
			.port(port)
			.contentType(ContentType.JSON)
			.body(new ContaBuilder()
						.atributos(b -> {
							b.descricao = anyString();
							b.data = LocalDate.now();
						}).build())
		.when()
			.post(URI)
		.then()
			.body("codErro[0]", equalTo("CT-4"))
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testeCadastroContaComId() {
				
		given()
			.port(port)
			.contentType(ContentType.JSON)
			.body(new ContaBuilder()
						.atributos(b -> {
							b.id = 10L; 
							b.descricao = anyString();
							b.valor = new BigDecimal(100); 
							b.data = LocalDate.now();
						}).build())
		.when()
			.post(URI)
		.then()
			.body("codErro[0]", equalTo("CT-1"))
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testeCastroContaValorIgualZero() {
		
		given()
			.port(port)
			.contentType(ContentType.JSON)
			.body(new ContaBuilder()
						.atributos(b -> {
							b.descricao = anyString();
							b.valor = new BigDecimal(0); 
							b.data = LocalDate.now();
						}).build())
			.when()
				.post(URI)
			.then()
				.body("codErro[0]", equalTo("CT-3"))
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testeBuscaPorData() {
		
		ContaEntity entity1  = new ContaEntity(); 
		entity1.setId(1L);
		entity1.setDescricao(anyString());
		entity1.setData(new LocalDate(2018, 12, 12));
		entity1.setValor(new BigDecimal(100L));
		
		ContaEntity entity2  = new ContaEntity(); 
		entity2.setId(2L);
		entity2.setDescricao(anyString());
		entity2.setData(new LocalDate(2018, 12, 1));
		entity2.setValor(new BigDecimal(100L));
		
		contaRepository.save(entity1);
		contaRepository.save(entity2);
		
		given()
			.port(port)
		.when()
			.get(URI + "/data/12-2018")
		.then()
			.body("data", hasSize(equalTo(2)))
			.statusCode(HttpStatus.OK.value());
	}
}
