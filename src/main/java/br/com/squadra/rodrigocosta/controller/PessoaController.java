package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.request.PessoaRequest;
import br.com.squadra.rodrigocosta.service.EnderecoService;
import br.com.squadra.rodrigocosta.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping(value = "/pessoa")
    public ResponseEntity<?> cadastraPessoa(@RequestBody @Validated PessoaRequest pessoaRequest) {
        service.salvaPessoaComEnderecos(pessoaRequest);
        return ResponseEntity.ok().build();
    }


}