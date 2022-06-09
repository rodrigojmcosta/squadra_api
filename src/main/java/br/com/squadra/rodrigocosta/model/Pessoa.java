package br.com.squadra.rodrigocosta.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PESSOA")
    @SequenceGenerator(name = "SEQUENCE_PESSOA", sequenceName = "SEQUENCE_PESSOA", allocationSize = 1)
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "IDADE")
    private Long idade;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    private Long status;

    public Pessoa() {

    }

    public Pessoa(String nome, String sobrenome, Long idade, String login, String senha, Long status) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.status = status;
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

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

}