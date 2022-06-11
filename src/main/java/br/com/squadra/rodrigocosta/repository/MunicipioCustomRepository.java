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

    public List<Municipio> busca(Long codigoMunicipio, Long codigoUF, String nome, Long status) {

        String queryComParametros = "SELECT * FROM TB_MUNICIPIO ";
        String querySemParametros = "SELECT * FROM TB_MUNICIPIO";
        String condicao = "WHERE";

        if (codigoMunicipio != null) {
            queryComParametros += condicao + " CODIGO_MUNICIPIO = :codigoMunicipio";
            condicao = " AND ";
        }

        if (codigoUF != null) {
            queryComParametros += condicao + " CODIGO_UF = :codigoUF";
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

        if (codigoMunicipio == null && codigoUF == null && nome == null && status == null) {
            return em.createQuery(querySemParametros, Municipio.class).getResultList();
        }

        if (codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
        }

        if (codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }

        if (nome != null) {
            q.setParameter("nome", nome);
        }

        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();

    }

    public List<Municipio> altera() {
        return null;
    }

}
