package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Uf;

public class UfResponse {

    private Long codigoUf;

    private String nome;

    private String sigla;

    private Long status;

    public UfResponse() {
    }

    public UfResponse(Long codigoUf, String nome, String sigla, Long status) {
        this.codigoUf = codigoUf;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public Long getCodigoUf() {
        return codigoUf;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Long getStatus() {
        return status;
    }

    public static UfResponse toResponse(Uf uf) {
        return new UfResponse(uf.getCodigoUf(), uf. getNome(), uf.getSigla(), uf.getStatus());
    }

}