package com.b2w.desafio.domain.service;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.domain.service.impl.PlanetaServiceImpl;
import com.b2w.desafio.infrastructure.handler.exception.RecurseNotFoundException;
import com.b2w.desafio.infrastructure.repository.PlanetaRepository;
import com.b2w.desafio.infrastructure.service.PlanetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaServiceImplUnitTest {

    @InjectMocks
    private PlanetaServiceImpl service;

    @Mock
    private PlanetaRepository repository;

    @Mock
    private PlanetService integrationService;

    @Test
    public void deveSalvarUmPlanetaComSucesso() {
        Planeta planetaAtual = new Planeta("Teste", "Hot", "Grass");
        Planeta planetaEsperado = new Planeta(1L, "Teste", "Hot", "Grass", 2);
        Planeta planetaRetorno = new Planeta(1L, "Teste", "Hot", "Grass", 2);

        Mockito.when(this.repository.save(planetaAtual)).thenReturn(planetaRetorno);
        Mockito.when(this.integrationService.getTotalDeFilmesPorPlaneta(planetaAtual)).thenReturn(2);
        Planeta planeta = this.service.save(planetaAtual);
        Assert.assertEquals(planetaEsperado, planeta);
    }

    @Test
    public void deveSalvarUmPlanetaSemNomeComSucesso() {
        Planeta planetaAtual = new Planeta(null, "Hot", "Grass");
        Planeta planetaEsperado = new Planeta(1L, null, "Hot", "Grass", null);
        Planeta planetaRetorno = new Planeta(1L, null, "Hot", "Grass", null);

        Mockito.when(this.repository.save(planetaAtual)).thenReturn(planetaRetorno);
        Mockito.when(this.integrationService.getTotalDeFilmesPorPlaneta(planetaAtual)).thenReturn(2);
        Planeta planeta = this.service.save(planetaAtual);
        Assert.assertEquals(planetaEsperado, planeta);
    }

    @Test
    public void deveExcluirUmPlanetaComSucesso() {
        Long id = 3L;
        Planeta planeta = new Planeta();
        planeta.setId(id);
        Optional<Planeta> planetaRetorno = Optional.of(planeta);

        Mockito.when(this.repository.findById(id)).thenReturn(planetaRetorno);
        this.service.delete(3L);

    }

    @Test(expected = RecurseNotFoundException.class)
    public void deveDarErroAoExcluirUmPlaneta() {
        Long id = 3L;
        Optional<Planeta> planetaRetorno = Optional.empty();

        Mockito.when(this.repository.findById(id)).thenReturn(planetaRetorno);
        this.service.delete(3L);
    }

    @Test
    public void deveBuscarTodosOsPlanetas() {
        List<Planeta> planetasRetornados = Arrays.asList(
                new Planeta(1L, "Shakuton", "Pakura", null, 0),
                new Planeta(2L, "Hidan", "Unknow", "pacific", 2),
                new Planeta(2L, "Kakuzu", "Unknow", "pacific", 5)
        );

        List<Planeta> planetasEsperados = Arrays.asList(
                new Planeta(1L, "Shakuton", "Pakura", null, 0),
                new Planeta(2L, "Hidan", "Unknow", "pacific", 2),
                new Planeta(2L, "Kakuzu", "Unknow", "pacific", 5)
        );

        Mockito.when(this.repository.findAllByNomeContaining("")).thenReturn(planetasRetornados);
        List<Planeta> planetas = this.service.findAll("");
        Assert.assertEquals(planetasEsperados, planetas);
    }

    @Test
    public void deveBuscarTodosOsPlanetasFiltradosPeloNome() {
        String nome = "zu";

        List<Planeta> planetasRetornados = Arrays.asList(
                new Planeta(1L, "Shakuton", "Pakura", null, 0),
                new Planeta(2L, "Hidan", "Unknow", "pacific", 2),
                new Planeta(2L, "Kakuzu", "Unknow", "pacific", 5)
        );

        List<Planeta> planetasEsperados = Arrays.asList(
                new Planeta(1L, "Shakuton", "Pakura", null, 0),
                new Planeta(2L, "Hidan", "Unknow", "pacific", 2),
                new Planeta(2L, "Kakuzu", "Unknow", "pacific", 5)
        );

        Mockito.when(this.repository.findAllByNomeContaining(nome)).thenReturn(planetasRetornados);
        List<Planeta> planetas = this.service.findAll(nome);
        Assert.assertEquals(planetasEsperados, planetas);
    }

    @Test
    public void deveBuscarUmPlanetaPorId() {
        Long id = 5L;
        Planeta planeta = new Planeta();
        planeta.setId(id);
        Optional<Planeta> planetaRetornado = Optional.of(planeta);
        Optional<Planeta> planetaEsperado = Optional.of(planeta);

        Mockito.when(this.repository.findById(id)).thenReturn(planetaRetornado);
        Optional<Planeta> planetaBanco = this.service.findById(id);
        Assert.assertEquals(planetaEsperado, planetaBanco);
    }

    @Test(expected = RecurseNotFoundException.class)
    public void deveDarErroAoBuscarUmPlanetaPorId() {
        Long id = 5L;
        Optional<Planeta> planetaRetornado = Optional.empty();
        Optional<Planeta> planetaEsperado = Optional.empty();

        Mockito.when(this.repository.findById(id)).thenReturn(planetaRetornado);
        Optional<Planeta> planetaBanco = this.service.findById(id);
        Assert.assertEquals(planetaEsperado, planetaBanco);
    }

}
