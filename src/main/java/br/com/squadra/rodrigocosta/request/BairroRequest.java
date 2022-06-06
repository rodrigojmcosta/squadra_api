package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BairroRequest {

    @NotNull
    private Long codigoMunicipio;

    @NotBlank
    private String nome;

    @NotNull
    private int status;

    public BairroRequest() {}

    public BairroRequest(Long codigoMunicipio, String nome, int status) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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
