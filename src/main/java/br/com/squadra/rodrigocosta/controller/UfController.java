package br.com.squadra.rodrigocosta.controller;


import br.com.squadra.rodrigocosta.request.UfRequest;
import br.com.squadra.rodrigocosta.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UfController {

    @Autowired
    UfService service;

    @PostMapping(value = "/uf")
    public void cadastrarUf(@RequestBody @Validated UfRequest ufRequest) {

//        try {
        service.cadastrarUf(ufRequest);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
//        }
//        return ResponseEntity.ok().build();
//    }

    }
}
