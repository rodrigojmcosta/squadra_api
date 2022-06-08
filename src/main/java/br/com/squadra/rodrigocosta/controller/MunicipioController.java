package br.com.squadra.rodrigocosta.controller;

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

import java.util.List;

@RestController
public class MunicipioController {

    @Autowired
    MunicipioService service;

    @Autowired
    UfService ufService;

    @PostMapping(value = "/municipio")
    public ResponseEntity<?> cadastraMunicipio(@RequestBody @Validated MunicipioRequest municipioRequest) {
        Uf ufMunicipio = ufService.encontraUfPorCodigoUf(municipioRequest.getCodigoUf());
        try {
            service.salvaMunicipio(municipioRequest, ufMunicipio);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(service.listaMunicipios());
    }

    @GetMapping(value = "/municipio")
    public ResponseEntity<?> listaMunicipioComParametros(@RequestParam(required = false) Long codigoMunicipio,
                                                         @RequestParam(required = false) Long codigoUf,
                                                         @RequestParam(required = false) String nome,
                                                         @RequestParam(required = false) Long status) {
        if (codigoMunicipio == null && codigoUf == null && nome == null && status == null) {
            List<MunicipioResponse> listaMunicipiosResponse = service.listaMunicipios();
            if (listaMunicipiosResponse.isEmpty()) {
                return null; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhum municipio
                // cadastrado no banco de dados!");
            } else {
                return ResponseEntity.ok().body(listaMunicipiosResponse);
            }
        } else {
            List<MunicipioResponse> listaMunicipiosResponse = service. listaMunicipiosComParametro(codigoMunicipio,
                    codigoUf, nome, status);
            if (listaMunicipiosResponse.isEmpty()) {
                return null; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenum municipio
                // cadastrado
                // no banco de dados" + " que corresponde aos valores que foram passados!");
            } else if (codigoMunicipio != null && codigoUf == null && nome == null && status == null) {
                return ResponseEntity.ok(listaMunicipiosResponse.stream().findFirst().get());
            } else {
                return ResponseEntity.ok().body(listaMunicipiosResponse);
            }
        }
    }

    @PutMapping(value = "/municipio")
    public ResponseEntity<?> atualizaMunicipio(@RequestBody MunicipioRequest municipioRequest) {
        Uf ufMunicipio = ufService.encontraUfPorCodigoUf(municipioRequest.getCodigoUf());
        try {
            return ResponseEntity.ok(service.atualizaMunicipio(municipioRequest, ufMunicipio));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }
}