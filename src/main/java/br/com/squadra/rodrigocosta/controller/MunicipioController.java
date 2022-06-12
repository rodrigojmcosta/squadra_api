package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.handler.Erro;
import br.com.squadra.rodrigocosta.model.Uf;
import br.com.squadra.rodrigocosta.request.MunicipioRequest;
import br.com.squadra.rodrigocosta.response.MunicipioResponse;
import br.com.squadra.rodrigocosta.service.MunicipioService;
import br.com.squadra.rodrigocosta.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MunicipioController {

    @Autowired
    MunicipioService service;

    @Autowired
    UfService ufService;

    @PostMapping(value = "/municipio")
    public ResponseEntity<?> cadastraMunicipio(@RequestBody @Validated MunicipioRequest municipioRequest) {
        Uf ufMunicipio = ufService.encontraUfPorCodigoUf(municipioRequest.getCodigoUF());
        try {
            service.salvaMunicipio(municipioRequest, ufMunicipio);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro("Não foi possível cadastrar o município" +
                    " no banco de dados!", HttpStatus.NOT_FOUND.value()));
        }
        return ResponseEntity.ok(service.listaMunicipios());
    }

    @GetMapping(value = "/municipio")
    public ResponseEntity<?> listaMunicipioComParametros(@RequestParam(required = false) Long codigoMunicipio,
                                                         @RequestParam(required = false) Long codigoUF,
                                                         @RequestParam(required = false) String nome,
                                                         @RequestParam(required = false) Long status) {
        List<MunicipioResponse> listaMunicipiosResponse = new ArrayList<>();
        if (codigoMunicipio == null && codigoUF == null && nome == null && status == null) {
            listaMunicipiosResponse = service.listaMunicipios();
            if (listaMunicipiosResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaMunicipiosResponse);
            }
        } else if (codigoMunicipio != null) {
            listaMunicipiosResponse = service.listaMunicipiosComParametro(codigoMunicipio, codigoUF, nome, status);
            if (listaMunicipiosResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaMunicipiosResponse.stream().findFirst().get());
            }
        }else if (codigoMunicipio == null && codigoUF != null && nome == null && status == null) {
            listaMunicipiosResponse = service.listaMunicipiosComParametro(codigoMunicipio, codigoUF, nome, status);
            if (listaMunicipiosResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaMunicipiosResponse);
            }
        } else if (codigoMunicipio == null && codigoUF == null && nome == null && status != null) {
            listaMunicipiosResponse = service.listaMunicipiosComParametro(codigoMunicipio, codigoUF, nome, status);
            if (listaMunicipiosResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaMunicipiosResponse);
            }
        } else {
            listaMunicipiosResponse = service.listaMunicipiosComParametro(codigoMunicipio,
                    codigoUF, nome, status);
            if (listaMunicipiosResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaMunicipiosResponse);
            }
        }
    }

    @PutMapping(value = "/municipio")
    public ResponseEntity<?> alteraMunicipio(@RequestBody MunicipioRequest municipioRequest) {
        Uf ufMunicipio = ufService.encontraUfPorCodigoUf(municipioRequest.getCodigoUF());
        try {
            return ResponseEntity.ok(service.atualizaMunicipio(municipioRequest, ufMunicipio));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível realizar a" +
                    " operação!",
                    HttpStatus.BAD_REQUEST.value()));
        }
    }
}