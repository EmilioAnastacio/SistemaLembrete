package com.example.demo.Controller;

import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody PessoaDTO pessoaDTO){
        try {
            Pessoa pessoa = pessoaService.cadastrar(pessoaDTO);

            if (pessoa == null) {
                return ResponseEntity.badRequest().body(null);
            }

            System.out.println("Nome: " + pessoa.getNome());

            return ResponseEntity.ok(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody Pessoa pessoa){
        try {
            pessoaService.editar(id,pessoa);
            return ResponseEntity.ok("Registro editado com sucesso!");
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<Pessoa> buscarPorId(@RequestParam("id") final Long id){
        try {
            pessoaService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPorId(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/buscar-nome")
    public ResponseEntity<List<PessoaDTO>> findByNome(@RequestParam("nome") String nome) {
        try {
            List<PessoaDTO> pessoasEncontradas = pessoaService.findByNome(nome);

            if (pessoasEncontradas.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 No Content se não houver resultados
            }

            return ResponseEntity.ok(pessoasEncontradas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request se houver um argumento inválido
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error para outros erros
        }
    }

}
