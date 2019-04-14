package com.b2w.desafio.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "planeta")
public class Planeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "clima", length = 100)
    private String clima;

    @Size(max = 100)
    @Column(name = "terreno", length = 100)
    private String terreno;

    @Transient
    private Integer totalDeAparicoesEmFilmes;

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }
}