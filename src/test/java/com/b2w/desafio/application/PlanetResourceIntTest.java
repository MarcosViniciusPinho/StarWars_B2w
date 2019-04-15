package com.b2w.desafio.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class PlanetResourceIntTest extends IntegrationSource {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveBuscarTodosOsPlanetas() throws Exception {
        this.mockMvc.perform(get("/planets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Alderaan"))
                .andExpect(jsonPath("$[0].climate").value("temperate"))
                .andExpect(jsonPath("$[0].terrain").value("grasslands, mountains"));
    }

}
