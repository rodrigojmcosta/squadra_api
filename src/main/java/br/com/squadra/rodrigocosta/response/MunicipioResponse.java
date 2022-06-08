package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Municipio;

public class MunicipioResponse {

    private Long codigoMunicipio;

    private Long codigoUf;

    private String nome;

    private Long status;

    public MunicipioResponse () {}

    public MunicipioResponse(Long codigoMunicipio, Long codigoUf, String nome, Long status) {
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

    public String getNome() {
        return nome;
    }

    public Long getStatus() {
        return status;
    }

    public static MunicipioResponse toResponse(Municipio municipio) { //
        return new MunicipioResponse(municipio.getCodigoMunicipio(), municipio.getCodigoUf(), municipio.getNome(), municipio.getStatus());
    }

}
