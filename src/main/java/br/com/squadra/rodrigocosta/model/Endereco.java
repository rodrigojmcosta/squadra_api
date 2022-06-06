package br.com.squadra.rodrigocosta.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ENDERECO")
    @SequenceGenerator(name = "SEQUENCE_ENDERECO", sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1)
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;

    @OneToOne(targetEntity = Bairro.class)
    @JoinColumn(name = "CODIGO_BAIRRO")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    private Pessoa pessoa;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private String cep;

    public Endereco() {

    }

    public Endereco(Bairro bairro, Pessoa pessoa, String nomeRua, int numero, String complemento, String cep) {
        this.bairro = bairro;
        this.pessoa = pessoa;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco(Bairro bairro, String nomeRua, int numero, String complemento, String cep) {
        this.bairro = bairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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

    @Override
    public String toString() {
        return "{" +
                "codigoEndereco=" + codigoEndereco +
                ", codigoBairro=" + bairro.getCodigoBairro() +
                ", codigoPessoa=" + pessoa.getCodigoPessoa() +
                ", nomeRua='" + nomeRua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}