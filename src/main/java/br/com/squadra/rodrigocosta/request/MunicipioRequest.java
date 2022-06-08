package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MunicipioRequest {

    private Long codigoMunicipio;

    @NotNull
    private Long codigoUf;

    @NotBlank
    private String nome;

    @NotNull
    private Long status;

    public MunicipioRequest() {
    }

    public MunicipioRequest(Long codigoUf, String nome, Long status) {
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.status = status;
    }

    public MunicipioRequest(Long codigoMunicipio, Long codigoUf, String nome, Long status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
