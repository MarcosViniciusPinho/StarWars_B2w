package com.b2w.desafio.application;

import com.b2w.desafio.domain.entity.Planeta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetaResourceIntTest extends IntegrationSource {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deve1SalvarUmPlanetaComSucesso() throws Exception {
        Planeta planeta = new Planeta("Tatooine", "arid", "1 standard");

        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(planeta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Tatooine"))
                .andExpect(jsonPath("$.clima").value("arid"))
                .andExpect(jsonPath("$.terreno").value("1 standard"))
                .andExpect(jsonPath("$.totalDeAparicoesEmFilmes").value(5)).andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("/planetas/1"));
    }

    @Test
    public void deve2SalvarUmPlanetaSemNomeComSucesso() throws Exception {
        Planeta planeta = new Planeta(null, "arid", "1 standard");

        MockHttpServletResponse response = this.mockMvc.perform(post("/planetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(planeta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").isEmpty())
                .andExpect(jsonPath("$.clima").value("arid"))
                .andExpect(jsonPath("$.terreno").value("1 standard")).andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("/planetas/2"));
    }

    @Test
    public void deve3BuscarTodosOsPlanetas() throws Exception {
        this.mockMvc.perform(get("/planetas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Tatooine"))
                .andExpect(jsonPath("$[0].clima").value("arid"))
                .andExpect(jsonPath("$[0].terreno").value("1 standard"));
    }

    @Test
    public void deve4BuscarTodosOsPlanetasFiltradosPeloNome() throws Exception {
        this.mockMvc.perform(get(String.format("/planetas?nome=%s", "ine"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Tatooine"))
                .andExpect(jsonPath("$[0].clima").value("arid"))
                .andExpect(jsonPath("$[0].terreno").value("1 standard"));
    }

    @Test
    public void deve5BuscarUmPlanetaPorIdComSucesso() throws Exception {
        this.mockMvc.perform(get("/planetas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Tatooine"))
                .andExpect(jsonPath("$.clima").value("arid"))
                .andExpect(jsonPath("$.terreno").value("1 standard"));
    }

    @Test
    public void deve6TentarBuscarUmPlanetaInexistentePorId() throws Exception {
        this.mockMvc.perform(get("/planetas/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deve7ExcluirUmPlanetaComSucesso() throws Exception {
        this.mockMvc.perform(delete("/planetas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deve8TentarExcluirUmPlanetaInexistente() throws Exception {
        this.mockMvc.perform(delete("/planetas/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
