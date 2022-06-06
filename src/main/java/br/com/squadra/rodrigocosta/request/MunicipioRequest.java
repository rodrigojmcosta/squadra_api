package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MunicipioRequest {

    @NotNull
    private Long codigoUf;

    @NotBlank
    private String nome;

    @NotNull
    private int status;

    public MunicipioRequest() {
    }

    public MunicipioRequest(Long codigoUf, String nome, int status) {
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(Long codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
