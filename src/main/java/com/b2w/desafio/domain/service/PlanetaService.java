package com.b2w.desafio.domain.service;

import com.b2w.desafio.domain.entity.Planeta;

import java.util.List;
import java.util.Optional;

public interface PlanetaService {

    /**
     * Método para salvar um planeta
     * @param planeta planeta
     * @return Planeta
     */
    Planeta save(Planeta planeta);

    /**
     * Método que exclui um determinado planeta por seu id.
     * @param id id
     */
    void delete(Long id);

    /**
     * Listar todos os planetas
     * @return List<Planeta>
     */
    List<Planeta> findAll();

    /**
     * Método usado para buscar um planeta a partir de seu id
     * @param id id
     * @return Planeta
     */
    Optional<Planeta> findById(Long id);

    /**
     * Método usado para buscar um planeta a partir de seu nome
     * @param nome nome
     * @return Planeta
     */
    Optional<Planeta> findByNome(String nome);
}
