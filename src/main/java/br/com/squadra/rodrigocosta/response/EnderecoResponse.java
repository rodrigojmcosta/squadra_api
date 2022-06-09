package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;

public class EnderecoResponse {

    private Long codigoEndereco;

    private Long codigoBairro;

    private Long codigoPessoa;

    private String nomeRua;

    private Long numero;

    private String complemento;

    private String cep;

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