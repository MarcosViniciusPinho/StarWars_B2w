package com.b2w.desafio.infrastructure.service;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.infrastructure.service.model.Result;

import java.util.List;

public interface PlanetService {

    /**
     * Método responsável por buscar todos os planetas na API https://swapi.co/api/planets
     * @param page page responsavel pela busca por demanda.
     * @return List<Result> classe que transforma as informações vindas da API para este objeto <code>Result</code>
     */
    List<Result> call(Long page);

    /**
     * Método responsável por obter o total de filmes por planeta disponibilizados na API deste desafio.
     * @param planeta planeta
     * @return Integer Retorna a quantidade de aparições em filmes caso o planeta fornecido exista
     * na API deste desafio, caso contrário retorna null
     */
    Integer getTotalDeFilmesPorPlaneta(Planeta planeta);

}
