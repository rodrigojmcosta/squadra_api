package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}