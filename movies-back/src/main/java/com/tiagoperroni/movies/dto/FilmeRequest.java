package com.tiagoperroni.movies.dto;

import lombok.Data;

@Data
public class FilmeRequest {

    private String nome;
    private String dataLancamento;
    private Long genero;
    private String descricao; 
    private String sinopse;
    private String imageUrl;

}
