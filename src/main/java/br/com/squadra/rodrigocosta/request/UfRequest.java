package br.com.squadra.rodrigocosta.request;

import br.com.squadra.rodrigocosta.model.Uf;

import javax.validation.constraints.*;

public class UfRequest {


    private Long codigoUF;

    @NotBlank(message = "O campo NOME não pode ser vazio!")
    private String nome;

    @NotBlank(message = "O campo SIGLA não pode ser vazio!")
    private String sigla;

    @NotNull
    @Min(value = 1, message = "O campo STATUS deve estar entre 1 e 2!")
    @Max(value = 2, message = "O campo STATUS deve estar entre 1 e 2!")
    private Long status;

    public UfRequest() {
    }

    public UfRequest(Long codigoUF, String nome, String sigla, Long status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public UfRequest(String nome, String sigla, Long status) {
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public static Uf toModel(UfRequest ufRequest) {
        return new Uf(ufRequest.getNome(), ufRequest.getSigla(), ufRequest.getStatus());
    }

}