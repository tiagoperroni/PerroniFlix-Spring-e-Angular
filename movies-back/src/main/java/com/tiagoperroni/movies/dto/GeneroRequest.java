package com.tiagoperroni.movies.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GeneroRequest {

    private String nome;
    private LocalDateTime dataCadastro;

}
