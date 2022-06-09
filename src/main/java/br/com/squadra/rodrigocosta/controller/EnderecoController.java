package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.request.EnderecoRequest;
import br.com.squadra.rodrigocosta.service.BairroService;
import br.com.squadra.rodrigocosta.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @Autowired
    BairroService bairroService;

    public ResponseEntity<?> cadastraEndereco (@RequestBody @Validated EnderecoRequest enderecoRequest) {
        Bairro bairroEndereco = bairroService.findBairroById(enderecoRequest.getCodigoBairro());
        try {
            //service.salvaEndereco(enderecoRequest, bairroEndereco);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().build();
    }

//    public

}
