package com.tiagoperroni.movies.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiagoperroni.movies.model.Filme;
import com.tiagoperroni.movies.model.Genero;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class FilmeResponse {

    private Long id;
    private String nome;
    private String dataLancamento;    
    private String imageUrl;  

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro;

    private Genero genero;
    private String descricao;   
    private String sinopse;

    public static FilmeResponse convertResponse(Filme filme) {
        var filmeResponse = new FilmeResponse();
        BeanUtils.copyProperties(filme, filmeResponse);
        return filmeResponse;
    }

}
