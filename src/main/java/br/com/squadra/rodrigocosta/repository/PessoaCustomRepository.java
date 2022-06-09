package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Pessoa;
import br.com.squadra.rodrigocosta.request.EnderecoRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PessoaCustomRepository {

    private final EntityManager em;

    public PessoaCustomRepository(EntityManager em) {
        this.em = em;
    }


    public List<Pessoa> busca(Long codigoPessoa, String nome, String sobrenome, Long idade, String login,
                              String senha, Long status) {

        String queryComParametros = "SELECT * FROM TB_PESSOA ";
        String querySemParametros = "SELECT * FROM TB_PESSOA";
        String condicao = "WHERE";

        if (codigoPessoa != null) {
            queryComParametros += condicao + " CODIGO_PESSOA = :codigoPessoa";
            condicao = " AND ";
        }

        if (idade != null) {
            queryComParametros += condicao + " IDADE = :idade";
            condicao = " AND ";
        }

        if (login != null) {
            queryComParametros += condicao + " LOGIN = :login";
            condicao = " AND ";
        }

        if (nome != null) {
            queryComParametros += condicao + " NOME = :nome";
        }

        if (senha != null) {
            queryComParametros += condicao + " SENHA = :senha";
        }

        if (sobrenome != null) {
            queryComParametros += condicao + " SOBRENOME = :sobrenome";
        }

        if (status != null) {
            queryComParametros += condicao + " STATUS = :status";
        }

        var q = em.createNativeQuery(queryComParametros, Pessoa.class);

        if (codigoPessoa == null && idade == null && login == null && nome == null && senha == null && sobrenome == null
                && status == null) {
            return em.createQuery(querySemParametros, Pessoa.class).getResultList();
        }

        if (codigoPessoa != null) {
            q.setParameter("codigoPessoa", codigoPessoa);
        }

        if (idade != null) {
            q.setParameter("idade", idade);
        }

        if (login != null) {
            q.setParameter("login", login);
        }

        if (nome != null) {
            q.setParameter("nome", nome);
        }

        if (senha != null) {
            q.setParameter("senha", senha);
        }

        if (sobrenome != null) {
            q.setParameter("sobrenome", sobrenome);
        }

        if (status != null) {
            q.setParameter("status", status);
        }

        return q.getResultList();

    }

}
