package br.com.squadra.rodrigocosta.controller;


import br.com.squadra.rodrigocosta.handler.Erro;
import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.request.UfRequest;
import br.com.squadra.rodrigocosta.response.UfResponse;
import br.com.squadra.rodrigocosta.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UfController {

    @Autowired
    UfService service;

    @PostMapping(value = "/uf")
    public ResponseEntity<?> cadastrarUf(@RequestBody @Validated UfRequest ufRequest) {
        service.cadastrarUf(ufRequest);
        return ResponseEntity.ok(service.listaUfs());
    }

    @GetMapping(value = "/uf")
    public ResponseEntity<?> listaUfsComParametros(@RequestParam(required = false) Long codigoUF,
                                                   @RequestParam(required = false) String nome,
                                                   @RequestParam(required = false) String sigla,
                                                   @RequestParam(required = false) Long status) {
        List<UfResponse> listaUfsResponse = new ArrayList<>();
        if (codigoUF == null && nome == null && sigla == null && status == null) {
            listaUfsResponse = service.listaUfs();
            if (listaUfsResponse.isEmpty()) {
                return ResponseEntity.ok().body(new ArrayList<>());
            } else {
                return ResponseEntity.ok().body(listaUfsResponse);
            }
        } else if (codigoUF == null && nome == null && sigla == null && status != null) {
            listaUfsResponse = service.listaUfsComParametro(codigoUF, nome, sigla, status);
            if (listaUfsResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaUfsResponse);
            }
        } else {
            listaUfsResponse = service.listaUfsComParametro(codigoUF, nome, sigla, status);
            if (listaUfsResponse.isEmpty()) {
                return ResponseEntity.ok(new UfResponse());
            } else if (listaUfsResponse.size() == 1) {
                return ResponseEntity.ok(listaUfsResponse.stream().findFirst().get()); //Retorna apenas um objeto
            } else {
                return ResponseEntity.ok(listaUfsResponse);
            }
        }
    }

    @PutMapping(value = "/uf")
    public ResponseEntity<?> atualizaUf(@RequestBody @Validated UfRequest ufRequest) {
        try {
            return ResponseEntity.ok(service.atualizaUf(ufRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro("Não foi possível alterar o registro" +
                    " no banco de dados!", HttpStatus.NOT_FOUND.value()));
        }
    }

}
