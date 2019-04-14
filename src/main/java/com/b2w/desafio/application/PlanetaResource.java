package com.b2w.desafio.application;

import com.b2w.desafio.domain.entity.Planeta;
import com.b2w.desafio.domain.service.PlanetaService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

    @Autowired
    private PlanetaService service;

    /**
     * Método que adiciona uma planeta
     * @param planeta planeta
     * @return ResponseEntity<Planeta>
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planeta> create(@Valid @RequestBody Planeta planeta){
        Planeta planetaSalvo = this.service.save(planeta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(planetaSalvo.getNome()).toUri();

        return ResponseEntity.created(uri).body(planetaSalvo);
    }

    /**
     * Método que exclui um planeta por seu id
     * @param id id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método que lista todos os planetas
     * @return ResponseEntity<List<Planeta>>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Planeta>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

    /**
     * Método que busca um planeta por seu id
     * @param  id id
     * @return ResponseEntity<Planeta>
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Planeta>> findById(@PathVariable(value = "id") Long id){
        val planeta = this.service.findById(id);
        return ResponseEntity.ok(planeta);
    }

    /**
     * Método que busca um planeta por seu nome
     * @param  nome nome
     * @return ResponseEntity<Planeta>
     */
    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Planeta>> findByNome(@PathVariable(value = "nome") String nome){
        val planeta = this.service.findByNome(nome);
        return ResponseEntity.ok(planeta);
    }

}
