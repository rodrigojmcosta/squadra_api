package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Municipio;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MunicipioResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoMunicipio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoUF;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("uf")
    private UfResponse ufResponse;

    public MunicipioResponse() {
    }

    public MunicipioResponse(Long codigoMunicipio, Long codigoUF, String nome, Long status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }

    public MunicipioResponse(Long codigoMunicipio, Long codigoUF, String nome, Long status, UfResponse ufResponse) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
        this.ufResponse = ufResponse;
    }

    public UfResponse getUfResponse() {
        return ufResponse;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public Long getCodigoUF() {
        return codigoUF;
    }

    public String getNome() {
        return nome;
    }

    public Long getStatus() {
        return status;
    }

    public static MunicipioResponse toResponse(Municipio municipio) { //
        return new MunicipioResponse(municipio.getCodigoMunicipio(), municipio.getUf().getCodigoUF(), municipio.getNome(),
                municipio.getStatus(), null);
    }

    public static MunicipioResponse toPutResponse(Municipio municipio) { //
        return new MunicipioResponse(municipio.getCodigoMunicipio(), municipio.getUf().getCodigoUF(),
                municipio.getNome(), municipio.getStatus());
    }

    public static MunicipioResponse toPessoaResponse(Municipio municipio) {
        return new MunicipioResponse(municipio.getCodigoMunicipio(), municipio.getUf().getCodigoUF(),
                municipio.getNome(), municipio.getStatus(), UfResponse.toResponse(municipio.getUf()));
    }

}
