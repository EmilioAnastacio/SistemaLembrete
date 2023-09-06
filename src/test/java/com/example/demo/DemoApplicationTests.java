package com.example.demo;

import com.example.demo.Controller.PessoaController;
import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repository.PessoaRepository;
import com.example.demo.Service.PessoaService;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@MockBean
	PessoaRepository repository;

	//private final PessoaController controller = new PessoaController();
	@Autowired
	private PessoaController controller;

	@Autowired
	private PessoaService pessoaService;

	@BeforeEach
	void injectData(){
		Optional<Pessoa> pessoa = Optional.of(new Pessoa(1l,"Emilio"));
		Long id = 1L;
		when(repository.findById(id)).thenReturn(pessoa);
		when(repository.save(Mockito.any(Pessoa.class))).thenAnswer(invocation -> {
			Pessoa pessoaSalva = invocation.getArgument(0);
			pessoaSalva.setId(2L);
			return pessoaSalva;
		});
	}

	@Test
	void testFindByIdPessoa(){
		var pessoacontroller = controller.buscarPorId(1L);
		Long id = pessoacontroller.getBody().getId().longValue();
		System.out.println(id);
		assertEquals(1L, id,0);
	}

	@Test
	void testCadastrarPessoa() {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setNome("Emilio");

		when(repository.save(Mockito.any(Pessoa.class))).thenAnswer(invocation -> {
			Pessoa pessoaSalva = invocation.getArgument(0);
			pessoaSalva.setId(2L); // Simulando um ID gerado pelo banco
			return pessoaSalva;
		});

		ResponseEntity<Pessoa> response = controller.cadastrar(pessoaDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody().getId());
		assertEquals("Emilio", response.getBody().getNome());
	}

	@Test
	void testEditarPessoa() {
		Long id = 1L;
		Pessoa pessoaEditada = new Pessoa(id, "Maria");

		when(repository.save(Mockito.any(Pessoa.class))).thenReturn(pessoaEditada);
		ResponseEntity<String> response = controller.editar(id, pessoaEditada);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Registro editado com sucesso!", response.getBody());
		System.out.println(response.getBody());
	}

	/* @Test
	void testBuscarPorNome(){
		var buscarNome = controller.findByNome("Emilio");
		String nome = buscarNome.getBody().toString();
		Assert.assertEquals("", "Emilio",buscarNome);

	}*/

	/*@Test
	void testBuscarPorNome(){
		ResponseEntity<List<PessoaDTO>> buscarNome = controller.findByNome("Emilio");

		if (buscarNome.getBody() != null) {
			List<PessoaDTO> pessoasEncontradas = buscarNome.getBody();

			// ve se a porra da lista nao ta vazia
			Assert.assertFalse(pessoasEncontradas.isEmpty());

			// verifica o nome da primeira pessoa
			assertEquals("Emilio", pessoasEncontradas.get(0).getNome());
			System.out.println("Nome da primeira pessoa: " + pessoasEncontradas.get(0).getNome());
		} else {
			// se nao tem nada é bodyNull
			Assert.assertNull(buscarNome.getBody());
			System.out.println("Nenhum resultado encontrado.");
		}
	}*/

	/*@Test
	void testBuscarPorNome(){
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setNome("Emilio");

		ResponseEntity<List<PessoaDTO>> buscarNome = controller.findByNome("Emilio");

		// Use assertEquals para verificar se o status da resposta é OK (200) quando há resultados
		assertEquals(HttpStatus.OK, buscarNome.getStatusCode());
		System.out.println(buscarNome);
		System.out.println(buscarNome.getBody());

		List<PessoaDTO> pessoasEncontradas = buscarNome.getBody();

		// Use assertEquals para verificar se a lista de pessoas encontradas está vazia quando não há resultados
		assertEquals(Collections.emptyList(), pessoasEncontradas);
	}
*/

}

