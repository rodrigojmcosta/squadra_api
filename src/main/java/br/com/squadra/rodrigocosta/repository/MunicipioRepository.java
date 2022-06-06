package br.com.squadra.rodrigocosta.repository;

import br.com.squadra.rodrigocosta.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}