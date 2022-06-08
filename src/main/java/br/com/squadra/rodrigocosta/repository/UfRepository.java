package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

}