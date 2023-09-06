package com.example.demo.Service;

import com.example.demo.DTO.PessoaDTO;
import com.example.demo.Entity.Pessoa;
import com.example.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Pessoa cadastrar(final PessoaDTO pessoaDTO){
        Assert.notNull(pessoaDTO.getNome(), "Error, campo nome vazio");
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(pessoaDTO.getNome());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editar(Long id,Pessoa pessoa){
        final Pessoa pessoaBanco = this.buscarPorId(id);
        if (pessoaBanco == null || !pessoaBanco.getId().equals(pessoa.getId())) {
            System.out.println(pessoaBanco.getId());
            System.out.println(pessoa.getId());
            throw new RuntimeException("não foi possivel identificar a pessoa informada!");
        }
        return pessoaRepository.save(pessoa);
    }


    public List<PessoaDTO> findByNome(String nome) {
        List<Pessoa> pessoasEncontradas = pessoaRepository.findByNome(nome);
        return pessoasEncontradas.stream()
                .map(pessoa -> new PessoaDTO(pessoa.getNome()))
                .collect(Collectors.toList());
    }

    public Pessoa buscarPorId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if (pessoaOptional.isPresent()) {
            return pessoaOptional.get();
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID: " + id);
        }
    }

//    @Transactional(rollbackFor = Exception.class)
//    public Pessoa buscarPorNome(String nome) {
//        return pessoaRepository.findByNome(nome);
//    }

}
