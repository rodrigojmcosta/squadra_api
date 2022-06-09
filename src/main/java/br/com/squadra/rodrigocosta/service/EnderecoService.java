package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Pessoa;
import br.com.squadra.rodrigocosta.repository.EnderecoCustomRepository;
import br.com.squadra.rodrigocosta.repository.EnderecoRepository;
import br.com.squadra.rodrigocosta.request.EnderecoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    EnderecoCustomRepository customRepository;

    public void salvaEndereco(EnderecoRequest enderecoRequest, Pessoa pessoa, Bairro bairro) {
        repository.save(EnderecoRequest.toModel(enderecoRequest, pessoa, bairro));
    }

    public void atualizaEndereco(EnderecoRequest enderecoRequest, Bairro bairroSalvo) {
        Optional<Endereco> enderecoBuscado = repository.findById(enderecoRequest.getCodigoEndereco());
        if (enderecoBuscado.isPresent()) {
            enderecoBuscado.get().setBairro(bairroSalvo);
            enderecoBuscado.get().setNomeRua(enderecoRequest.getNomeRua());
            enderecoBuscado.get().setNumero(enderecoRequest.getNumero());
            enderecoBuscado.get().setComplemento(enderecoRequest.getComplemento());
            enderecoBuscado.get().setCep(enderecoRequest.getCep());
            repository.save(enderecoBuscado.get());
        }
    }
}