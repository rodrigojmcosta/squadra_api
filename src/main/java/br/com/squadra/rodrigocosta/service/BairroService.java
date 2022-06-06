package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.repository.BairroRepository;
import br.com.squadra.rodrigocosta.request.BairroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    BairroRepository repository;

    public void salvaBairro(BairroRequest bairroRequest, Municipio municipioBairro) throws NullPointerException {
        if (municipioBairro != null) {
            repository.save(new Bairro(municipioBairro, bairroRequest.getNome(), bairroRequest.getStatus()));
        } else {
            throw new NullPointerException("O código recebido não corresponde a nenhum MUNICIPIO cadastrado no banco!");
        }
    }

    public Bairro findBairroById(Long codigoBairro) {
        Optional<Bairro> bairroBuscado = repository.findById(codigoBairro);
        return bairroBuscado.orElse(null);
    }
}
