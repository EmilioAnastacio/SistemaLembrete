package com.example.demo.DTO;

import com.example.demo.Entity.Pessoa;
import lombok.Data;

import java.util.Date;

@Data
public class LembreteDTO {
    private String conteudoLembrete;
    private Long idPessoa;

}
