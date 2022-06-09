package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BairroRequest {

    private Long codigoBairro;

    @NotNull
    private Long codigoMunicipio;

    @NotBlank
    private String nome;

    @NotNull
    private Long status;

    public BairroRequest() {
    }

    public BairroRequest(Long codigoMunicipio, String nome, Long status) {
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public BairroRequest(Long codigoBairro, Long codigoMunicipio, String nome, Long status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
