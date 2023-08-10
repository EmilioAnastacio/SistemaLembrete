package com.example.demo.DTO;

import com.example.demo.Entity.Pessoa;
import lombok.Data;

import java.util.Date;

@Data
public class LembreteDTO {
    private String conteudo;
    private Long idPessoa;

    public LembreteDTO(String conteudo, Long idPessoa) {
        this.conteudo = conteudo;
        this.idPessoa = idPessoa;
    }

    public LembreteDTO() {
    }
}
