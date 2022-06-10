package br.com.squadra.rodrigocosta.request;

import br.com.squadra.rodrigocosta.model.Pessoa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PessoaRequest {

    private Long codigoPessoa;

    @NotBlank(message = "O nome da pessoa não pode estar vazio!")
    private String nome;

    @NotBlank(message = "O sobrenome da pessoa não pode ser vazio!")
    private String sobrenome;

    private Long idade;

    @NotBlank(message = "O login da pessoa não pode ser vazio!")
    private String login;

    @NotBlank(message = "O senha da pessoa não pode ser vazio!")
    private String senha;

    private Long status;

    @NotEmpty(message = "A pessoa precisa ter pelo menos um endereço válido!")
    private List<EnderecoRequest> enderecos;

    public PessoaRequest() {
    }

    public PessoaRequest(String nome, String sobrenome, Long idade, String login, String senha, Long status,
                         List<EnderecoRequest> enderecos) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }

    public PessoaRequest(Long codigoPessoa, String nome, String sobrenome, Long idade, String login, String senha,
                         Long status, List<EnderecoRequest> enderecos) {
        this.codigoPessoa = codigoPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.enderecos = enderecos;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
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

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<EnderecoRequest> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoRequest> enderecos) {
        this.enderecos = enderecos;
    }

    public static Pessoa toModel(PessoaRequest pessoaRequest) {
        return new Pessoa(pessoaRequest.getNome(), pessoaRequest.getSobrenome(),
                pessoaRequest.getIdade(), pessoaRequest.getLogin(), pessoaRequest.getSenha(), pessoaRequest.getStatus());
    }
}
