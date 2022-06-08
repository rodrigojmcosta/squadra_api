package br.com.squadra.rodrigocosta.request;

import br.com.squadra.rodrigocosta.model.Uf;
import org.springframework.util.Assert;

import javax.validation.constraints.*;

public class UfRequest {

    private Long codigoUf;

    @NotBlank(message = "O campo NOME não pode ser vazio!")
    private String nome;

    @NotBlank(message = "O campo SIGLA não pode ser vazio!")
    private String sigla;

    @NotNull
    @Min(value = 1, message = "O campo STATUS deve estar entre 1 e 2!")
    @Max(value = 2, message = "O campo STATUS deve estar entre 1 e 2!")
    private int status;

    public UfRequest() {
    }

    public UfRequest(Long codigoUf, String nome, String sigla, int status) {
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public UfRequest(String nome, String sigla, int status) {
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Uf toModel(UfRequest ufRequest) {
        return new Uf(ufRequest.getNome(), ufRequest.getSigla(), ufRequest.getStatus());
    }

}