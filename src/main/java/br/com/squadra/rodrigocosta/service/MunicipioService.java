package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.repository.MunicipioCustomRepository;
import br.com.squadra.rodrigocosta.repository.MunicipioRepository;
import br.com.squadra.rodrigocosta.request.MunicipioRequest;
import br.com.squadra.rodrigocosta.response.MunicipioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    MunicipioRepository repository;

    @Autowired
    MunicipioCustomRepository customRepository;

    public void salvaMunicipio(MunicipioRequest municipioRequest, Uf ufMunicipio) throws NullPointerException {
        if (ufMunicipio != null) {
            repository.save(new Municipio(ufMunicipio, municipioRequest.getNome(), municipioRequest.getStatus()));
        } else {
            throw new NullPointerException("O código recebido não corresponde a nenhuma UF cadastrada no banco!");
        }
    }

    public Municipio encontraMunicipioPorCodigoMunicipio(Long codigoMunicipio) {
        Optional<Municipio> municipioBuscado = repository.findById(codigoMunicipio);
        return municipioBuscado.orElse(null);
    }

    public List<MunicipioResponse> listaMunicipios() {
        List<Municipio> listaMunicipios = repository.findAll();
        List<MunicipioResponse> listaMunicipioResponse = new ArrayList<>();
        for (Municipio municipio : listaMunicipios) {
            listaMunicipioResponse.add(MunicipioResponse.toResponse(municipio));
        }
        return listaMunicipioResponse;
    }

    public List<MunicipioResponse> listaMunicipiosComParametro(Long codigoMunicipio, Long codigoUf, String nome, Long status) {
        List<Municipio> listaMunicipios = customRepository.busca(codigoMunicipio, codigoUf, nome, status);
        List<MunicipioResponse> listaMunicipioResponse = new ArrayList<>();
        for (Municipio municipio : listaMunicipios) {
            listaMunicipioResponse.add(MunicipioResponse.toResponse(municipio));
        }
        return listaMunicipioResponse;
    }

    public List<MunicipioResponse> atualizaMunicipio(MunicipioRequest municipioRequest, Uf ufMunicipio) {
        Optional<Municipio> municipioBuscado = repository.findById(municipioRequest.getCodigoMunicipio());
        if (municipioBuscado.isPresent() && ufMunicipio != null) {
            municipioBuscado.get().setNome(municipioRequest.getNome());
            municipioBuscado.get().setStatus(municipioRequest.getStatus());
            municipioBuscado.get().setUf(ufMunicipio);
            repository.save(municipioBuscado.get());
            List<Municipio> municipios = repository.findAll();
            List<MunicipioResponse> municipiosResponse = new ArrayList<>();
            for (Municipio municipio : municipios) {
                municipiosResponse.add(MunicipioResponse.toPutResponse(municipio));
            }
            return municipiosResponse;
        } else {
            throw new NullPointerException("Não foi possível encontrar nenhum municipio no banco de dados com o codigoMunicipio" + " referenciado!");
        }
    }
}