package br.com.squadra.rodrigocosta.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_BAIRRO")
    @SequenceGenerator(name = "SEQUENCE_BAIRRO", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio municipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Long status;

    public Bairro() {

    }

    public Bairro(Municipio municipio, String nome, Long status) {
        this.municipio = municipio;
        this.nome = nome;
        this.status = status;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    @Override
    public String toString() {
        return "{" +
                "codigoBairro=" + codigoBairro +
                ", codigoMunicipio=" + municipio.getCodigoMunicipio() +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }

    public void setCodigoMunicipioBairro(Long codigoMunicipio) {
    }
}
