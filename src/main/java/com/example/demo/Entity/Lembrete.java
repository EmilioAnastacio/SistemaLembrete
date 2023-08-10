package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "lembrete", schema = "public")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")@Getter@Setter
    private Long id;

    @Getter@Setter
    @Column(name = "conteudo")
    private String conteudo;

    @Getter@Setter
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa idPessoa;

  public Lembrete(){

  }

  public Lembrete(String conteudo, Pessoa idPessoa) {
        this.conteudo = conteudo;
        this.idPessoa = idPessoa;
  }
}
