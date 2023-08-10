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
import org.springframework.util.Assert;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Transactional(rollbackFor = Exception.class)
    public Lembrete cadastrar(final LembreteDTO lembreteDTO) {

        Lembrete lembrete = new Lembrete();
        lembrete.setConteudo(lembreteDTO.getConteudo());

        Pessoa pessoa = pessoaService.buscarPorId(lembreteDTO.getIdPessoa());
        lembrete.setIdPessoa(pessoa);

       return lembreteRepository.save(lembrete);

    }
    @Transactional(rollbackFor = Exception.class)
    public List<Lembrete> findByNomePessoa(String nome) {

        Assert.isTrue(nome != null, "Nome nao inserido");
        Assert.isTrue(nome.length() <= 100, "nome muito grande, passa dos padroes, INVALIDO");

        return lembreteRepository.findByNomePessoa(nome);
    }


}

