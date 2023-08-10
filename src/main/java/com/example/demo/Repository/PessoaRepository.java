package com.example.demo.Repository;

import com.example.demo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("from Pessoa where nome = :nome")
    public List<Pessoa> findByNome(@Param("nome") final String nome);

}
