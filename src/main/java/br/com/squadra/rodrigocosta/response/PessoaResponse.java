package br.com.squadra.rodrigocosta.response;

import br.com.squadra.rodrigocosta.model.Endereco;
import br.com.squadra.rodrigocosta.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaResponse {


    private Long codigoPessoa;

    private Long idade;

    private String login;

    private String nome;

    private String senha;

    private String sobrenome;

    private List<EnderecoResponse> enderecos = new ArrayList<>();

    private Long status;

    public PessoaResponse() {
    }

    public PessoaResponse(Long codigoPessoa, String nome, String sobrenome, Long idade, String login, String senha,
                          Long status) {
        this.codigoPessoa = codigoPessoa;
        this.idade = idade;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.sobrenome = sobrenome;
        this.status = status;
    }

    public PessoaResponse(Long codigoPessoa, String nome, String sobrenome, Long idade, String login, String senha,
                          List<EnderecoResponse> enderecos, Long status) {
        this.codigoPessoa = codigoPessoa;
        this.idade = idade;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.sobrenome = sobrenome;
        this.enderecos = enderecos;
        this.status = status;
    }

    public static List<PessoaResponse> toPutResponse(List<Pessoa> pessoas) {
        List<PessoaResponse> listaPessoaResponse = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            listaPessoaResponse.add(new PessoaResponse(pessoa.getCodigoPessoa(), pessoa.getNome(), pessoa.getSobrenome(),
                    pessoa.getIdade(), pessoa.getLogin(), pessoa.getSenha(), new ArrayList<>(), pessoa.getStatus()));
        }
        return listaPessoaResponse;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public Long getIdade() {
        return idade;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Long getStatus() {
        return status;
    }

    public List<EnderecoResponse> getEnderecos() {
        return enderecos;
    }

    public static PessoaResponse toResponse(Pessoa pessoa, List<EnderecoResponse> enderecos) {
        return new PessoaResponse(pessoa.getCodigoPessoa(), pessoa.getNome(), pessoa.getSobrenome(), pessoa.getIdade(),
                pessoa.getLogin(), pessoa.getSenha(), enderecos, pessoa.getStatus());
    }

}
