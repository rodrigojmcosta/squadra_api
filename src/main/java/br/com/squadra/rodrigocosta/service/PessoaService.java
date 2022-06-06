package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Pessoa;
import br.com.squadra.rodrigocosta.repository.BairroRepository;
import br.com.squadra.rodrigocosta.repository.EnderecoRepository;
import br.com.squadra.rodrigocosta.repository.PessoaRepository;
import br.com.squadra.rodrigocosta.request.EnderecoRequest;
import br.com.squadra.rodrigocosta.request.PessoaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    BairroRepository bairroRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public void salvaPessoa(PessoaRequest pessoaRequest) {
        Pessoa pessoaSeraSalva = new Pessoa(pessoaRequest.getNome(), pessoaRequest.getSobrenome(),
                pessoaRequest.getIdade(), pessoaRequest.getLogin(), pessoaRequest.getSenha(), pessoaRequest.getStatus());
        Pessoa pessoaSalva = pessoaRepository.save(pessoaSeraSalva);
        List<Endereco> listaEnderecosPessoa = new ArrayList<>();
        for (EnderecoRequest enderecoRequest : pessoaRequest.getEnderecos()) {
            listaEnderecosPessoa.add(new Endereco(getBairro(enderecoRequest.getCodigoBairro()), pessoaSalva, enderecoRequest.getNomeRua(), enderecoRequest.getNumero(),
                    enderecoRequest.getComplemento(), enderecoRequest.getCep()));
        }
        pessoaSalva.getEnderecos().addAll(listaEnderecosPessoa);
        enderecoRepository.saveAll(listaEnderecosPessoa);
        pessoaRepository.save(pessoaSalva);
    }

    private Bairro getBairro(Long codigoBairro) {
        Optional<Bairro> bairroBuscado = bairroRepository.findById(codigoBairro);
        if (bairroBuscado.isPresent()) {
            return bairroBuscado.get();
        } else {
            throw new NullPointerException("O código de bairro referenciado não corresponde a nenhum bairro" +
                    " cadastrado no banco de dados!");
        }
    }
}
