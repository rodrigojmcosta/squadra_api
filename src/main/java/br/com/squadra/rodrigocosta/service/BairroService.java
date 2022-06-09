package br.com.squadra.rodrigocosta.service;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.repository.BairroCustomRepository;
import br.com.squadra.rodrigocosta.repository.BairroRepository;
import br.com.squadra.rodrigocosta.request.BairroRequest;
import br.com.squadra.rodrigocosta.response.BairroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    BairroRepository repository;

    @Autowired
    BairroCustomRepository customRepository;

    public List<BairroResponse> salvaBairro(BairroRequest bairroRequest, Municipio municipioBairro) throws Exception {
        if (municipioBairro != null) {
            repository.save(new Bairro(municipioBairro, bairroRequest.getNome(), bairroRequest.getStatus()));
            List<BairroResponse> listaBairroResponse = new ArrayList<>();
            List<Bairro> listaBairros = repository.findAll();
            for (Bairro bairro : listaBairros) {
                listaBairroResponse.add(new BairroResponse(bairro.getCodigoBairro(), bairro.getMunicipio().getCodigoMunicipio(),
                        bairro.getNome(), bairro.getStatus()));
            }
            return listaBairroResponse;
        } else {
            throw new Exception();
        }
    }

    public Bairro findBairroById(Long codigoBairro) {
        Optional<Bairro> bairroBuscado = repository.findById(codigoBairro);
        return bairroBuscado.orElse(null);
    }

    public List<BairroResponse> listaBairros() {
        List<Bairro> listaBairros = repository.findAll();
        List<BairroResponse> listaBairroResponse = new ArrayList<>();
        for (Bairro bairro : listaBairros) {
            listaBairroResponse.add(BairroResponse.toResponse(bairro));
        }
        return listaBairroResponse;
    }

    public List<BairroResponse> listaBairrosComParametro(Long codigoBairro, Long codigoMunicipio, String nome, Long status) {
        List<Bairro> listaBairros = customRepository.find(codigoBairro, codigoMunicipio, nome, status);
        List<BairroResponse> listaBairroResponse = new ArrayList<>();
        for (Bairro bairro : listaBairros) {
            listaBairroResponse.add(BairroResponse.toResponse(bairro));
        }
        return listaBairroResponse;
    }

    public List<BairroResponse> atualizaBairro(BairroRequest bairroRequest, Municipio municipioBairro) {
        Optional<Bairro> bairroBuscado = repository.findById(bairroRequest.getCodigoBairro());
        if (bairroBuscado.isPresent() && municipioBairro != null) {
            bairroBuscado.get().setNome(bairroRequest.getNome());
            bairroBuscado.get().setStatus(bairroRequest.getStatus());
            bairroBuscado.get().setMunicipio(municipioBairro);
            repository.save(bairroBuscado.get());
            List<Bairro> bairros = repository.findAll();
            List<BairroResponse> bairrosResponse = new ArrayList<>();
            for (Bairro bairro : bairros) {
                bairrosResponse.add(BairroResponse.toPutResponse(bairro));
            }
            return bairrosResponse;
        } else {
            throw new NullPointerException("Não foi possível encontrar nenhum bairro no banco de dados com o codigoBairro" + " referenciado!");
        }
    }
}
