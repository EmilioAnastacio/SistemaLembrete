package com.example.demo.DTO;
import com.example.demo.Entity.Pessoa;
import lombok.Data;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;

    public PessoaDTO() {
    }

    public PessoaDTO( String nome) {
        this.nome = nome;
    }
}
