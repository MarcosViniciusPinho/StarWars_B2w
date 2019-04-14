package com.b2w.desafio.infrastructure.repository;

import com.b2w.desafio.domain.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

    /**
     * Método para buscar um planeta a partir de seu nome
     * @param nome nome
     * @return Optional<Planeta>
     */
    Optional<Planeta> findByNome(String nome);

}

