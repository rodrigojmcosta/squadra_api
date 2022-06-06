package br.com.squadra.rodrigocosta.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PessoaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotNull
    private int idade;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotNull
    private int status;

    @NotEmpty
    private List<EnderecoRequest> enderecos;

    public PessoaRequest() {
    }

    public PessoaRequest(String nome, String sobrenome, int idade, String login, String senha, int status, List<EnderecoRequest> enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<EnderecoRequest> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoRequest> enderecos) {
        this.enderecos = enderecos;
    }
}
