package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pessoa", schema = "public")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")@Getter@Setter
    private Long id;

    @Getter@Setter
    @Column(name = "nome")
    private String nome;

}
