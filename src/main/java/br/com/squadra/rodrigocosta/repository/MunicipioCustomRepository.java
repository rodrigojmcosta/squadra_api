package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Municipio;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MunicipioCustomRepository {

    private final EntityManager em;

    public MunicipioCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Municipio> find(Long codigoMunicipio, Long codigoUf, String nome, Long status) {

        String queryComParametros = "SELECT * FROM TB_MUNICIPIO ";
        String querySemParametros = "SELECT * FROM TB_MUNICIPIO";
        String condicao = "WHERE";

        if (codigoMunicipio != null) {
            queryComParametros += condicao + " CODIGO_MUNICIPIO = :codigoMunicipio";
            condicao = " AND ";
        }

        if (codigoUf != null) {
            queryComParametros += condicao + " CODIGO_UF = :codigoUf";
            condicao = " AND ";
        }

        if (nome != null) {
            queryComParametros += condicao + " NOME = :nome";
            condicao = " AND ";
        }

        if (status != null) {
            queryComParametros += condicao + " STATUS = :status";
        }

        var q = em.createNativeQuery(queryComParametros, Municipio.class);

        if (codigoMunicipio == null && codigoUf == null && nome == null && status == null) {
            return em.createQuery(querySemParametros, Municipio.class).getResultList();
        }

        if (codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
        }

        if (codigoUf != null) {
            q.setParameter("codigoUf", codigoUf);
        }

        if (nome != null) {
            q.setParameter("nome", nome);
        }

        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();

    }

}
