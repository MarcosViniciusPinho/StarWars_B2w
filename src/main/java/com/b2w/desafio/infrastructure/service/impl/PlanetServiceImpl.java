package com.b2w.desafio.infrastructure.service.impl;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.infrastructure.DesafioProperty;
import com.b2w.desafio.infrastructure.service.PlanetService;
import com.b2w.desafio.infrastructure.service.model.Planet;
import com.b2w.desafio.infrastructure.service.model.Result;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    private RestTemplate restTemplate;

    @Autowired
    private DesafioProperty property;

    public PlanetServiceImpl() {
        this.restTemplate = HttpSwapi.initialiaze();
    }

    /**
     * Método responsável por buscar todos os planetas na API https://swapi.co/api/planets
     * @param page page responsavel pela busca por demanda.
     * @return List<Result> classe que transforma as informações vindas da API para este objeto <code>Result</code>
     */
    private List<Result> call(Long page) {
        StringBuilder sb = new StringBuilder().append(this.property.getEnviroment())
                .append("/planets?page=%d");
        Planet planet = this.restTemplate.getForObject(
                String.format(sb.toString(), page), Planet.class
        );
        return planet != null ? planet.getResults(): new ArrayList<>();
    }

    @Override
    public Integer getTotalDeFilmesPorPlaneta(Planeta planeta) {
        Long cont = 1L;
        Optional<Result> result = Optional.empty();

        do {

            List<Result> results = this.call(cont);

            if(CollectionUtils.isNotEmpty(results)) {
                result = results.stream()
                        .filter(it -> it.getName().equals(planeta.getNome())).findFirst();
                cont++;
            }

            if(results.size() < 10 || result.isPresent()){
                cont = 0L;
            }

        } while(cont > 0);

        return result.isPresent() ? result.get().getFilms().size() : null;
    }

    @Override
    public List<Result> findAll() {
        Long cont = 1L;
        List<Result> resultsOther = new ArrayList<>();

        do {

            List<Result> results = this.call(cont);

            if(CollectionUtils.isNotEmpty(results)) {
                resultsOther.addAll(results);
                cont++;
            }

            if(results.size() < 10){
                cont = 0L;
            }

        } while(cont > 0);

        return resultsOther;
    }

}
