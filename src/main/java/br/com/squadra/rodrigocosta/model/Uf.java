package br.com.squadra.rodrigocosta.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_UF")
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_UF")
    @SequenceGenerator(name = "SEQUENCE_UF", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    @Column(name = "CODIGO_UF")
    private Long codigoUf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SIGLA")
    private String sigla;

    @OneToMany(mappedBy = "uf")
    private List<Municipio> municipios;

    @Column(name = "STATUS")
    private Long status;

    public Uf() {
    }

    public Uf(String nome, String sigla, Long status) {
        this.nome = nome;
        this.sigla = sigla;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCodigoUf() {
        return codigoUf;
    }

    @Override
    public String toString() {
        return "{" +
                "codigoUf=" + codigoUf +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", status=" + status +
                '}';
    }

}