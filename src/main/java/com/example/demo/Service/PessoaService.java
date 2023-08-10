package com.example.demo.Service;

import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Pessoa cadastrar(final PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(pessoaDTO.getNome());
        return pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class)
    public Pessoa buscarPorNome(String nome) {
        return pessoaRepository.findByNome(nome);
    }

}
