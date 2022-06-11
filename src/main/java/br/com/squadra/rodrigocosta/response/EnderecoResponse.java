package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;

public class EnderecoResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoEndereco;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoBairro;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long codigoPessoa;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nomeRua;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long numero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String complemento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cep;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BairroResponse bairro;


    public EnderecoResponse() {
    }

    public EnderecoResponse(Long codigoEndereco, Long codigoBairro, Long codigoPessoa, String nomeRua, Long numero,
                            String complemento, String cep, BairroResponse bairro) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public Long getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public BairroResponse getBairro() {
        return bairro;
    }

    public static EnderecoResponse toResponse(Endereco endereco) {
        return new EnderecoResponse(endereco.getCodigoEndereco(), endereco.getBairro().getCodigoBairro(),
                endereco.getPessoa().getCodigoPessoa(), endereco.getNomeRua(), endereco.getNumero(),
                endereco.getComplemento(), endereco.getCep(), BairroResponse.toPessoaResponse(endereco.getBairro()));
    }

}