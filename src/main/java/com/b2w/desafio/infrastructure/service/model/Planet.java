package com.b2w.desafio.infrastructure.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Planet {

    private Long count;
    private String next;
    private String previous;
    private List<Result> results;

}
