package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.handler.Erro;
import br.com.squadra.rodrigocosta.model.Municipio;
import br.com.squadra.rodrigocosta.request.BairroRequest;
import br.com.squadra.rodrigocosta.response.BairroResponse;
import br.com.squadra.rodrigocosta.service.BairroService;
import br.com.squadra.rodrigocosta.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BairroController {

    @Autowired
    BairroService service;

    @Autowired
    MunicipioService municipioService;

    @PostMapping(value = "/bairro")
    public ResponseEntity<?> cadastrarBairro(@RequestBody @Validated BairroRequest bairroRequest) {
        Municipio municipioBairro = municipioService.encontraMunicipioPorCodigoMunicipio(bairroRequest.getCodigoMunicipio());
        try {
            return ResponseEntity.ok(service.salvaBairro(bairroRequest, municipioBairro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível realizar a operação!", 400));
        }

    }

    @GetMapping(value = "/bairro")
    public ResponseEntity<?> listaBairroComParametros(@RequestParam(required = false) Long codigoBairro,
                                                      @RequestParam(required = false) Long codigoMunicipio,
                                                      @RequestParam(required = false) String nome,
                                                      @RequestParam(required = false) Long status) {
        try {
            if (codigoBairro == null && codigoMunicipio == null && nome == null && status == null) {
                List<BairroResponse> listaBairrosResponse = service.listaBairros();
                if (listaBairrosResponse.isEmpty()) {
                    return null; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhum bairro
                    // cadastrado no banco de dados!");
                } else {
                    return ResponseEntity.ok().body(listaBairrosResponse);
                }
            } else {
                List<BairroResponse> listaBairrosResponse = service.listaBairrosComParametro(codigoBairro,
                        codigoMunicipio, nome, status);
                BairroResponse bairroResponse = new BairroResponse();
                if (listaBairrosResponse.isEmpty()) {
                    return ResponseEntity.ok(listaBairrosResponse); //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenum bairro
                    // cadastrado
                    // no banco de dados" + " que corresponde aos valores que foram passados!");
                } else if (codigoBairro != null && codigoMunicipio == null && nome == null && status == null) {
                    return ResponseEntity.ok(listaBairrosResponse.stream().findFirst().get());
                } else {
                    return ResponseEntity.ok().body(listaBairrosResponse);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível realizar a operação!", 400));
        }
    }

    @PutMapping(value = "/bairro")
    public ResponseEntity<?> alteraBairro(@RequestBody BairroRequest bairroRequest) {
        Municipio municipioBairro = municipioService.encontraMunicipioPorCodigoMunicipio(bairroRequest.getCodigoMunicipio());
        try {
            return ResponseEntity.ok(service.atualizaBairro(bairroRequest, municipioBairro));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível realizar a operação!", 400));
        }
    }

}
