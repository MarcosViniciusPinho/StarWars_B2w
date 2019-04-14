package com.b2w.desafio.infrastructure.service;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.infrastructure.service.model.Result;

import java.util.List;

public interface PlanetService {

    /**
     * Método responsável por obter o total de filmes por planeta disponibilizados na API deste desafio.
     * @param planeta planeta
     * @return Integer Retorna a quantidade de aparições em filmes caso o planeta fornecido exista
     * na API deste desafio, caso contrário retorna null
     */
    Integer getTotalDeFilmesPorPlaneta(Planeta planeta);

    /**
     * Método responsável por listar todas os planetas da API deste desafio.
     * @return List<Result>
     */
    List<Result> findAll();

}
