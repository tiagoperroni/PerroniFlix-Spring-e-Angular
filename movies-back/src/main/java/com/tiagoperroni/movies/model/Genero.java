package com.tiagoperroni.movies.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiagoperroni.movies.dto.GeneroRequest;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Entity
@Data
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro = LocalDateTime.now();


    public static Genero convert(GeneroRequest generoRequest) {
        var genero = new Genero();
        BeanUtils.copyProperties(generoRequest, genero, "dataCadastro");        
        return genero;
    }
}
