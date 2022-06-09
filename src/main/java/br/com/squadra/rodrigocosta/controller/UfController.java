package br.com.squadra.rodrigocosta.controller;


import br.com.squadra.rodrigocosta.handler.Erro;
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
    public ResponseEntity<?> listaUfsComParametros(@RequestParam(required = false) Long codigoUf,
                                                   @RequestParam(required = false) String nome,
                                                   @RequestParam(required = false) String sigla,
                                                   @RequestParam(required = false) Long status) {
        if (codigoUf == null && nome == null && sigla == null && status == null) {
            List<UfResponse> listaUfsResponse = service.listaUfs();
            if (listaUfsResponse.isEmpty()) {
                return ResponseEntity.ok().body(new ArrayList<>());
            } else {
                return ResponseEntity.ok().body(listaUfsResponse);
            }
        } else {
            List<UfResponse> listaUfsResponse = service.listaUfsComParametro(codigoUf, nome, sigla, status);
            if (codigoUf != null && nome == null && sigla == null && status == null) {
                if (listaUfsResponse.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro("Não foi possível encontrar nenhuma UF com este código!", HttpStatus.NOT_FOUND.value()));
                }
                return ResponseEntity.ok(listaUfsResponse.stream().findFirst().get()); //Retorna apenas um objeto
            } else if (listaUfsResponse.isEmpty()) {
                return ResponseEntity.ok().body(new ArrayList<>());
            } else {
                return ResponseEntity.ok().body(listaUfsResponse);
            }
        }
    }

    @PutMapping(value = "/uf")
    public ResponseEntity<?> atualizaUf(@RequestBody @Validated UfRequest ufRequest) {
        try {
            return ResponseEntity.ok(service.atualizaUf(ufRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro("Não foi possível alterar o registro no banco de dados!", HttpStatus.NOT_FOUND.value()));
        }
    }

}
