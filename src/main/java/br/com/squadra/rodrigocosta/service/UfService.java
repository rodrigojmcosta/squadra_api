package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.repository.UfCustomRepository;
import br.com.squadra.rodrigocosta.repository.UfRepository;
import br.com.squadra.rodrigocosta.request.UfRequest;
import br.com.squadra.rodrigocosta.response.UfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UfService {

    @Autowired
    UfRepository repository;

    @Autowired
    UfCustomRepository customRepository;

    public void cadastrarUf(UfRequest ufRequest) {
        repository.save(UfRequest.toModel(ufRequest));
    }


    public Uf encontraUfPorCodigoUf(Long codigoUF) {
        Optional<Uf> ufBuscada = repository.findById(codigoUF);
        return ufBuscada.orElse(null); //Método retorna a Uf que foi buscada e, caso não exista, retorna nulo.
    }

    public List<UfResponse> listaUfs() {
        List<Uf> listaUfs = repository.findAll();
        List<UfResponse> listaUfResponse = new ArrayList<>();
        for (Uf uf : listaUfs) {
            listaUfResponse.add(UfResponse.toResponse(uf));
        }
        return listaUfResponse;
    }

    public List<UfResponse> listaUfsComParametro(Long codigoUf, String nome, String sigla, Long status) {
        List<Uf> listaUfs = customRepository.busca(codigoUf, nome, sigla, status);
        List<UfResponse> listaUfResponse = new ArrayList<>();
        for (Uf uf : listaUfs) {
            listaUfResponse.add(UfResponse.toResponse(uf));
        }
        return listaUfResponse;
    }

    public List<Uf> atualizaUf(UfRequest ufRequest) throws Exception {
        Optional<Uf> ufBuscada = repository.findById(ufRequest.getCodigoUF());
        if (ufBuscada.isPresent()) {
            ufBuscada.get().setNome(ufRequest.getNome());
            ufBuscada.get().setSigla(ufRequest.getSigla());
            ufBuscada.get().setStatus(ufRequest.getStatus());
            repository.save(ufBuscada.get());
            return repository.findAll();
        } else {
            throw new Exception();
        }
    }
}
