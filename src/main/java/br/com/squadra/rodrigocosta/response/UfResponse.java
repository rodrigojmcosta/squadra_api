package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Uf;
import com.fasterxml.jackson.annotation.JsonInclude;

public class UfResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoUF;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sigla;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long status;

    public UfResponse() {
    }

    public UfResponse(Long codigoUF, String nome, String sigla, Long status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public Long getCodigoUF() {
        return codigoUF;
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
        return new UfResponse(uf.getCodigoUF(), uf. getNome(), uf.getSigla(), uf.getStatus());
    }

}