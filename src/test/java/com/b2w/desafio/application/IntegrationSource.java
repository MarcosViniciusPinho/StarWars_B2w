package com.b2w.desafio.application;

import com.b2w.desafio.DesafioApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DesafioApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class IntegrationSource {
}