package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
}