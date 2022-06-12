package br.com.squadra.rodrigocosta.controller;

import br.com.squadra.rodrigocosta.handler.Erro;
import br.com.squadra.rodrigocosta.request.PessoaRequest;
import br.com.squadra.rodrigocosta.response.EnderecoResponse;
import br.com.squadra.rodrigocosta.response.PessoaResponse;
import br.com.squadra.rodrigocosta.service.EnderecoService;
import br.com.squadra.rodrigocosta.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping(value = "/pessoa")
    public ResponseEntity<?> cadastraPessoa(@RequestBody @Validated PessoaRequest pessoaRequest) {
        try {
            return ResponseEntity.ok(service.salvaPessoaComEnderecos(pessoaRequest));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível" +
                    " realizar a operação no banco de dados!!", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping(value = "/pessoa")
    public ResponseEntity<?> listaPessoaComParametros(@RequestParam(required = false) Long codigoPessoa,
                                                      @RequestParam(required = false) String nome,
                                                      @RequestParam(required = false) String sobrenome,
                                                      @RequestParam(required = false) Long idade,
                                                      @RequestParam(required = false) String login,
                                                      @RequestParam(required = false) String senha,
                                                      @RequestParam(required = false) Long status) {
        List<PessoaResponse> listaPessoaResponse = new ArrayList<>();
        if (codigoPessoa == null && nome == null && sobrenome == null && idade == null && login == null &&
                senha == null && status == null) {
            listaPessoaResponse = service.listaPessoas();
            if (listaPessoaResponse.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(listaPessoaResponse);
            }
        } else {
            listaPessoaResponse = service.listaPessoasComParametro(codigoPessoa, idade, login, nome,
                    senha, sobrenome, status);
            if (listaPessoaResponse.isEmpty()) {
                if (codigoPessoa != null) {
                    return ResponseEntity.ok(new ArrayList<>());
                } else {
                    return ResponseEntity.ok(listaPessoaResponse);
                }
            } else if (codigoPessoa != null) {
                return ResponseEntity.ok(listaPessoaResponse.stream().findFirst().get());
            } else if (status != null) {
                return ResponseEntity.ok(listaPessoaResponse);
            } else if (listaPessoaResponse.size() == 1) {
                return ResponseEntity.ok(listaPessoaResponse.stream().findFirst().get());
            } else {
                return ResponseEntity.ok(listaPessoaResponse);
            }
        }
    }

    @PutMapping(value = "/pessoa")
    public ResponseEntity<?> atualizaPessoa(@RequestBody @Validated PessoaRequest pessoaRequest) {
        try {
            return ResponseEntity.ok(service.atualizaPessoa(pessoaRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Erro("Não foi possível realizar a" +
                    " operação!", 400));
        }
    }
}