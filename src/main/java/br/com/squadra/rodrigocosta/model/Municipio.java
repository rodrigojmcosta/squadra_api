package br.com.squadra.rodrigocosta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_MUNICIPIO")
    @SequenceGenerator(name = "SEQUENCE_MUNICIPIO", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private Uf uf;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bairro> bairros;

    @Column(name = "STATUS")
    private Long status;

    public Municipio() {

    }

    public Municipio(Uf uf, String nome, Long status) {
        this.uf = uf;
        this.nome = nome;
        this.status = status;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    @Override
    public String toString() {
        return "{" +
                "codigoMunicipio=" + codigoMunicipio +
                ", codigoUF=" + uf.getCodigoUF() +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }

}