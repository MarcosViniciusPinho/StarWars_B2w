package com.b2w.desafio.infrastructure.repository;

import com.b2w.desafio.domain.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

    /**
     * Método que lista os planetas na base de dados, tendo parametros ou não.
     * @param nome nome
     * @return List<Planeta>
     */
    List<Planeta> findAllByNomeContaining(String nome);
}

