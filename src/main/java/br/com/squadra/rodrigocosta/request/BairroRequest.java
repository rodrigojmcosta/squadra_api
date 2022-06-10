package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BairroRequest {

    private Long codigoBairro;

    @NotNull(message = "O código do município não pode estar vazio!")
    private Long codigoMunicipio;

    @NotBlank(message = "O nome do município não pode estar vazio!")
    private String nome;

    @NotNull(message = "O status do município não pode estar vazio!")
    @Min(value = 1, message = "O campo STATUS deve estar entre 1 e 2!")
    @Max(value = 2, message = "O campo STATUS deve estar entre 1 e 2!")
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
