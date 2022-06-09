package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Endereco;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EnderecoCustomRepository {

    private final EntityManager em;


    public EnderecoCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Endereco> busca(Long codigoPessoa) {

        String queryComParametros = "SELECT * FROM TB_ENDERECO ";
        String querySemParametros = "SELECT * FROM TB_ENDERECO";
        String condicao = "WHERE";

        if (codigoPessoa != null) {
            queryComParametros += condicao + " CODIGO_PESSOA = :codigoPessoa";
            condicao = " AND ";
            var q = em.createNativeQuery(queryComParametros, Endereco.class);
            q.setParameter("codigoPessoa", codigoPessoa);
            return q.getResultList();
        } else {
            return em.createQuery(querySemParametros, Endereco.class).getResultList();
        }

    }

}