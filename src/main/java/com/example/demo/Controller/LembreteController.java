package com.example.demo.Controller;

import com.example.demo.DTO.LembreteDTO;
import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Lembrete;
import com.example.demo.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Lembrete> cadastrar(@RequestBody LembreteDTO lembreteDTO) {
        try {
            Lembrete novoLembrete = lembreteService.cadastrar(lembreteDTO);

            if (novoLembrete == null) {
                return ResponseEntity.badRequest().body(null);
            }

            System.out.println("ID do Lembrete: " + novoLembrete.getId());

            return ResponseEntity.ok(novoLembrete);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("/buscarNome")
    public ResponseEntity<List<Lembrete>> findByNomePessoa(@RequestParam("nome") String nome) {
        try {
            Assert.isTrue(nome != null && !nome.isEmpty() && nome.length() <= 100, "Nome inválido.");

            List<Lembrete> lembretesEncontrados = lembreteService.findByNomePessoa(nome);

            if (lembretesEncontrados.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(lembretesEncontrados);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
