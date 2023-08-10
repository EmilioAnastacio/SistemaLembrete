package com.example.demo.Controller;

import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar-nome")
    public ResponseEntity<List<PessoaDTO>> findByNome(@RequestParam("nome") String nome) {
        try {
            List<PessoaDTO> pessoasEncontradas = pessoaService.findByNome(nome);

            if (pessoasEncontradas.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(pessoasEncontradas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
