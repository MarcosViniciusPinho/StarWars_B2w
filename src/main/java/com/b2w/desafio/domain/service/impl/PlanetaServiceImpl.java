package com.b2w.desafio.domain.service.impl;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.domain.service.PlanetaService;
import com.b2w.desafio.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.desafio.infrastructure.handler.exception.UniqueException;
import com.b2w.desafio.infrastructure.repository.PlanetaRepository;
import com.b2w.desafio.infrastructure.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    @Autowired
    private PlanetaRepository repository;

    @Autowired
    private PlanetService service;

    @Override
    public Planeta save(Planeta planeta) {
        this.validarNomeRepetido(planeta.getNome());
        Planeta planetaCadastrado = this.repository.save(planeta);
        planetaCadastrado.setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta)
        );
        return planetaCadastrado;
    }

    @Override
    public void delete(Long id) {
        Optional<Planeta> planeta = this.repository.findById(id);
        this.validarRecurso(planeta);
        this.repository.delete(planeta.get());
    }

    @Override
    public List<Planeta> findAll() {
        List<Planeta> planetas = this.repository.findAll();
        planetas.forEach(planeta -> {
            planeta.setTotalDeAparicoesEmFilmes(
                    this.service.getTotalDeFilmesPorPlaneta(planeta)
            );
        });
        return planetas;
    }

    @Override
    public Optional<Planeta> findById(Long id) {
        Optional<Planeta> planeta = this.repository.findById(id);
        this.validarRecurso(planeta);
        planeta.get().setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta.get())
        );
        return planeta;
    }

    @Override
    public Optional<Planeta> findByNome(String nome) {
        Optional<Planeta> planeta = this.repository.findByNome(nome);
        this.validarRecurso(planeta);
        planeta.get().setTotalDeAparicoesEmFilmes(
                this.service.getTotalDeFilmesPorPlaneta(planeta.get())
        );
        return planeta;
    }

    private void validarRecurso(Optional<Planeta> planeta) {
        if(!planeta.isPresent()) {
            throw new RecurseNotFoundException("Não foi(foram) encontrado(s) planeta(s) com os dados informados",
                    "A API do desafio não retornou nenhuma informação!");
        }
    }

    private void validarNomeRepetido(String nome) {
        Optional<Planeta> planeta = this.repository.findByNome(nome);
        if(planeta.isPresent()) {
            throw new UniqueException("Já existe um planeta cadastrado com este nome",
                    "Planeta já existente na base de dados!");
        }
    }

}
