package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Bairro;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BairroCustomRepository {


    private final EntityManager em;

    public BairroCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Bairro> find(Long codigoBairro, Long codigoMunicipio, String nome, Long status) {

        String queryComParametros = "SELECT * FROM TB_BAIRRO ";
        String querySemParametros = "SELECT * FROM TB_BAIRRO";
        String condicao = "WHERE";

        if (codigoBairro != null) {
            queryComParametros += condicao + " CODIGO_BAIRRO = :codigoBairro";
            condicao = " AND ";
        }

        if (codigoMunicipio != null) {
            queryComParametros += condicao + " CODIGO_MUNICIPIO = :codigoMunicipio";
            condicao = " AND ";
        }

        if (nome != null) {
            queryComParametros += condicao + " NOME = :nome";
            condicao = " AND ";
        }

        if (status != null) {
            queryComParametros += condicao + " STATUS = :status";
        }

        var q = em.createNativeQuery(queryComParametros, Bairro.class);

        if (codigoBairro == null && codigoMunicipio == null && nome == null && status == null) {
            return em.createQuery(querySemParametros, Bairro.class).getResultList();
        }

        if (codigoBairro != null) {
            q.setParameter("codigoBairro", codigoBairro);
        }

        if (codigoMunicipio != null) {
            q.setParameter("codigoMunicipio", codigoMunicipio);
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
