package br.com.squadra.rodrigocosta.request;

import br.com.squadra.rodrigocosta.model.Bairro;
import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Pessoa;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {

    private Long codigoEndereco;

    @NotNull(message = "O código do bairro não pode ser vazio!")
    private Long codigoBairro;

    @NotNull(message = "O código da pessoa não pode ser vazio!")
    private Long codigoPessoa;

    @NotEmpty(message = "O nome da rua não pode ser vazio!")
    private String nomeRua;

    @NotNull(message = "O número da rua não pode ser vazio!")
    private Long numero;

    @NotEmpty(message = "O complemento do endereço não pode ser vazio!")
    private String complemento;

    @NotEmpty(message = "O cep do endereço não pode ser vazio!")
    private String cep;

    public EnderecoRequest() {
    }

    public EnderecoRequest(Long codigoBairro, Long codigoPessoa, String nomeRua, Long numero, String complemento, String cep) {
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public EnderecoRequest(Long codigoEndereco, Long codigoBairro, Long codigoPessoa, String nomeRua,
                           Long numero, String complemento, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.codigoPessoa = codigoPessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public static Endereco toModel(EnderecoRequest enderecoRequest, Pessoa pessoaSalva, Bairro bairroBuscado) {
        return new Endereco(bairroBuscado, pessoaSalva, enderecoRequest.getNomeRua(), enderecoRequest.getNumero(),
                enderecoRequest.getComplemento(), enderecoRequest.getCep());
    }
}