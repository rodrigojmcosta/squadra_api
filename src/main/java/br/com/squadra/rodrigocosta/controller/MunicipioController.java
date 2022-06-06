package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.request.MunicipioRequest;
import br.com.squadra.rodrigocosta.service.MunicipioService;
import br.com.squadra.rodrigocosta.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MunicipioController {

    @Autowired
    MunicipioService service;

    @Autowired
    UfService ufService;

    @PostMapping(value = "/municipio")
    public ResponseEntity<?> cadastraMunicipio(@RequestBody @Validated MunicipioRequest municipioRequest) {
        Uf ufMunicipio = ufService.findUfById(municipioRequest.getCodigoUf());
        try {
            service.salvaMunicipio(municipioRequest, ufMunicipio);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().build();
    }

}