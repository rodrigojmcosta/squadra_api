package br.com.squadra.rodrigocosta.controller;


import br.com.squadra.rodrigocosta.request.UfRequest;
import br.com.squadra.rodrigocosta.response.UfResponse;
import br.com.squadra.rodrigocosta.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UfController {

    @Autowired
    UfService service;

    @PostMapping(value = "/uf")
    public ResponseEntity<?> cadastrarUf(@RequestBody @Validated UfRequest ufRequest) {

        try {
            service.cadastrarUf(ufRequest);
            service.listaUfs();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/uf")
    public ResponseEntity<?> listaUfsComParametros(@RequestParam(required = false) Long codigoUf,
                                                   @RequestParam(required = false) String nome,
                                                   @RequestParam(required = false) String sigla,
                                                   @RequestParam(required = false) Long status) {
        if (codigoUf == null && nome == null && sigla == null && status == null) {
            List<UfResponse> listaUfsResponse = service.listaUfs();
            if (listaUfsResponse.isEmpty()) {
                return null; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma UF
                // cadastrada no banco de dados!");
            } else {
                return ResponseEntity.ok().body(listaUfsResponse);
            }
        } else {
            List<UfResponse> listaUfsResponse = service.listaUfsComParametro(codigoUf, nome, sigla, status);
            if (listaUfsResponse.isEmpty()) {
                return null; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma UF cadastrada
                // no banco de dados" + " que corresponde aos valores que foram passados!");
            } else if (codigoUf != null && nome == null && sigla == null && status == null) {
                return ResponseEntity.ok(listaUfsResponse.stream().findFirst().get()); //Retorna apenas um único
                // objeto
            } else {
                return ResponseEntity.ok().body(listaUfsResponse);
            }
        }
    }

    @PutMapping(value = "/uf")
    public ResponseEntity<?> atualizaUf(@RequestBody @Validated UfRequest ufRequest) {
        try {
            return ResponseEntity.ok(service.atualizaUf(ufRequest));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

}
