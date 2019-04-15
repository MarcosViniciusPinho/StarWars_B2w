package com.b2w.desafio.application;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.domain.service.PlanetaService;
import com.b2w.desafio.infrastructure.service.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PlanetaResourceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetaService service;

    @MockBean
    private PlanetService integrationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveSalvarUmPlanetaComSucesso() throws Exception {
        Planeta planeta = new Planeta("Teste", "Hot", "Grass");
        Planeta planetaRetorno = new Planeta(1L, "Teste", "Hot", "Grass", 2);

        Mockito.when(this.integrationService.getTotalDeFilmesPorPlaneta(planeta)).thenReturn(2);
        Mockito.when(this.service.save(planeta)).thenReturn(planetaRetorno);

        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(planeta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Teste"))
                .andExpect(jsonPath("$.clima").value("Hot"))
                .andExpect(jsonPath("$.terreno").value("Grass"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(2)).andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("/planetas/1"));
    }

    @Test
    public void deveSalvarUmPlanetaSemNomeComSucesso() throws Exception {
        Planeta planeta = new Planeta("", "Hot", "Grass");
        Planeta planetaRetorno = new Planeta(1L, "", "Hot", "Grass", 2);

        Mockito.when(this.integrationService.getTotalDeFilmesPorPlaneta(planeta)).thenReturn(2);
        Mockito.when(this.service.save(planeta)).thenReturn(planetaRetorno);

        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(planeta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(""))
                .andExpect(jsonPath("$.clima").value("Hot"))
                .andExpect(jsonPath("$.terreno").value("Grass"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(2)).andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("/planetas/1"));
    }

    @Test
    public void deveExcluirUmPlanetaComSucesso() throws Exception {
        this.mockMvc.perform(delete("/planetas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveBuscarUmPlanetaPorId() throws Exception{
        Planeta planetaRetorno = new Planeta(1L, "Hackaton", "Hot", "Grass", 2);

        Mockito.when(this.service.findById(1L)).thenReturn(Optional.of(planetaRetorno));

        this.mockMvc.perform(get("/planetas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Hackaton"))
                .andExpect(jsonPath("$.clima").value("Hot"))
                .andExpect(jsonPath("$.terreno").value("Grass"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(2));
    }

}
