package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.request.BairroRequest;
import br.com.squadra.rodrigocosta.service.BairroService;
import br.com.squadra.rodrigocosta.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BairroController {

    @Autowired
    BairroService service;

    @Autowired
    MunicipioService municipioService;

    @PostMapping(value = "/bairro")
    public ResponseEntity<?> cadastrarBairro(@RequestBody @Validated BairroRequest bairroRequest) {
        Municipio municipioBairro = municipioService.findMunicipioById(bairroRequest.getCodigoMunicipio());
        try {
            service.salvaBairro(bairroRequest, municipioBairro);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().build();
    }
}
