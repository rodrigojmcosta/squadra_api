package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.repository.MunicipioRepository;
import br.com.squadra.rodrigocosta.request.MunicipioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    MunicipioRepository repository;

    public void salvaMunicipio(MunicipioRequest municipioRequest, Uf ufMunicipio) throws NullPointerException {
        if (ufMunicipio != null) {
            repository.save(new Municipio(ufMunicipio, municipioRequest.getNome(), municipioRequest.getStatus()));
        } else {
            throw new NullPointerException("O código recebido não corresponde a nenhuma UF cadastrada no banco!");
        }
    }

    public Municipio findMunicipioById(Long codigoMunicipio) {
        Optional<Municipio> municipioBuscado = repository.findById(codigoMunicipio);
        return municipioBuscado.orElse(null);
    }
}