package com.tiagoperroni.movies.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiagoperroni.movies.model.Genero;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class GeneroResponse {
    
    private Long id;
    private String nome;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro;

    public static GeneroResponse convert(Genero save) {
        var generoResponse = new GeneroResponse();
        BeanUtils.copyProperties(save, generoResponse);
        return generoResponse;
    }

}
