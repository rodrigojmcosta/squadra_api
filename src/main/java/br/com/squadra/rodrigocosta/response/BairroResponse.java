package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Bairro;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BairroResponse {

    private Long codigoBairro;

    private Long codigoMunicipio;

    private String nome;

    private Long status;

    @JsonProperty("municipio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MunicipioResponse municipioResponse;

    public BairroResponse() {
    }

    public BairroResponse(Long codigoBairro, Long codigoMunicipio, String nome, Long status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
    }

    public BairroResponse(Long codigoBairro, Long codigoMunicipio, String nome, Long status, MunicipioResponse municipioResponse) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        this.nome = nome;
        this.status = status;
        this.municipioResponse = municipioResponse;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public String getNome() {
        return nome;
    }

    public Long getStatus() {
        return status;
    }

    public MunicipioResponse getMunicipioResponse() {
        return municipioResponse;
    }

    public static BairroResponse toResponse(Bairro bairro) {
        return new BairroResponse(bairro.getCodigoBairro(), bairro.getMunicipio().getCodigoMunicipio(), bairro.getNome(), bairro.getStatus(), null);
    }

    public static BairroResponse toPessoaResponse(Bairro bairro) {
        return new BairroResponse(bairro.getCodigoBairro(), bairro.getMunicipio().getCodigoMunicipio(), bairro.getNome(), bairro.getStatus(), MunicipioResponse.toPessoaResponse(bairro.getMunicipio()));
    }

    public static BairroResponse toPutResponse(Bairro bairro) {
        return new BairroResponse(bairro.getCodigoBairro(), bairro.getMunicipio().getCodigoMunicipio(), bairro.getNome(), bairro.getStatus());
    }

}
