package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.repository.UfRepository;
import br.com.squadra.rodrigocosta.request.UfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UfService {

    @Autowired
    UfRepository repository;

    public void cadastrarUf(UfRequest ufRequest) {
        repository.save(UfRequest.toModel(ufRequest));
    }


    public Uf findUfById(Long codigoUf) {
        Optional<Uf> ufBuscada = repository.findById(codigoUf);
        return ufBuscada.orElse(null); //Método retorna a Uf que foi buscada e, caso não exista, retorna nulo.
    }
}
