package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MunicipioRequest {

    private Long codigoMunicipio;

    @NotNull(message = "O código da UF não pode ser vazio!")
    private Long codigoUF;

    @NotBlank(message = "O nome do município não pode ser vazio!")
    private String nome;

    @NotNull(message = "O status do município não pode ser vazio!")
    @Min(value = 1, message = "O campo STATUS deve estar entre 1 e 2!")
    @Max(value = 2, message = "O campo STATUS deve estar entre 1 e 2!")
    private Long status;

    public MunicipioRequest() {
    }

    public MunicipioRequest(Long codigoUF, String nome, Long status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public MunicipioRequest(Long codigoMunicipio, Long codigoUF, String nome, Long status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
