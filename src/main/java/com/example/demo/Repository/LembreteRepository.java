package com.example.demo.Repository;

import com.example.demo.Entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    @Query("from Lembrete where idPessoa.nome = :nome")
    List<Lembrete> findByNomePessoa(@Param("nome") String nome);

}
