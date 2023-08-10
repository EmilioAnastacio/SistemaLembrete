package com.example.demo.Controller;

import com.example.demo.DTO.LembreteDTO;
import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Lembrete;
import com.example.demo.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @PostMapping
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

    @GetMapping("/buscarPessoa")
    public ResponseEntity<List<Lembrete>> buscarPorPessoa(@RequestParam("idPessoa") Long idPessoa) {
        try {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setId(idPessoa);

            List<Lembrete> lembretesEncontrados = lembreteService.buscarPorPessoa(pessoaDTO);

            if (lembretesEncontrados.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(lembretesEncontrados);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
