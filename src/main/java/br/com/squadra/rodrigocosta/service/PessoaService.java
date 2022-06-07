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

    public void salvaPessoaComEnderecos(PessoaRequest pessoaRequest) {
        Pessoa pessoaSeraSalva = PessoaRequest.toModel(pessoaRequest);
        Pessoa pessoaSalva = pessoaRepository.save(pessoaSeraSalva);
        List<Endereco> listaEnderecosPessoaSalva = new ArrayList<>();
        for (EnderecoRequest enderecoRequest : pessoaRequest.getEnderecos()) {
            listaEnderecosPessoaSalva.add(EnderecoRequest.toModel(enderecoRequest, pessoaSalva, getBairro(enderecoRequest.getCodigoBairro())));
        }
        enderecoRepository.saveAll(listaEnderecosPessoaSalva);
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
