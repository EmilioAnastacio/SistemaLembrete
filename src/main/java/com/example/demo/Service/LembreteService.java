package com.example.demo.Service;

import com.example.demo.DTO.LembreteDTO;
import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Lembrete;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repository.LembreteRepository;
import com.example.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Lembrete cadastrar(final LembreteDTO lembreteDTO) {

        Lembrete lembrete = new Lembrete();
        lembrete.setConteudo(lembreteDTO.getConteudoLembrete());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(lembreteDTO.getIdPessoa());
        lembrete.setIdPessoa(pessoa);

        return lembreteRepository.save(lembrete);
    }
    @Transactional(rollbackFor = Exception.class)
    public List<Lembrete> buscarPorPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        return lembreteRepository.findByPessoa(pessoa.getId());
    }
}

