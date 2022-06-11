package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Uf;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UfCustomRepository {

    private final EntityManager em;

    public UfCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Uf> busca(Long codigoUF, String nome, String sigla, Long status) {

        String querySemParametros = "SELECT * FROM TB_UF";
        String queryComParametros = "SELECT * FROM TB_UF ";
        String condicao = "WHERE";

        if (codigoUF != null) {
            queryComParametros += condicao + " CODIGO_UF = :codigoUF";
            condicao = " AND ";
        }

        if (nome != null) {
            queryComParametros += condicao + " NOME = :nome";
            condicao = " AND ";
        }

        if (sigla != null) {
            queryComParametros += condicao + " SIGLA = :sigla";
            condicao = " AND ";
        }

        if (status != null) {
            queryComParametros += condicao + " STATUS = :status";
        }

        var q = em.createNativeQuery(queryComParametros, Uf.class);

        if (codigoUF == null && nome == null && sigla == null && status == null) {
            return em.createQuery(querySemParametros, Uf.class).getResultList();
        }

        if (codigoUF != null) {
            q.setParameter("codigoUF", codigoUF);
        }

        if (nome != null) {
            q.setParameter("nome", nome);
        }

        if (sigla != null) {
            q.setParameter("sigla", sigla);
        }

        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();

    }

}
