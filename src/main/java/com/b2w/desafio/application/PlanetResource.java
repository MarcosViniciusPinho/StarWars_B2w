package com.b2w.desafio.application;

import com.b2w.desafio.infrastructure.service.PlanetService;
import com.b2w.desafio.infrastructure.service.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    @Autowired
    private PlanetService service;

    /**
     * Método que lista todos os planetas
     * @return ResponseEntity<List<Result>>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Result>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

}
