package com.tiagoperroni.movies.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.tiagoperroni.movies.dto.FilmeRequest;
import com.tiagoperroni.movies.dto.FilmeResponse;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Entity
@Data
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    @Column(name = "data_lancamento")
    private String dataLancamento;

    @ManyToOne(targetEntity = Genero.class)
    private Genero genero;
    private String descricao;
    @Column(length = 1000)
    private String sinopse;

    private String imageUrl;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();
    
    public static Filme convert(FilmeRequest filmeRequest) {
        var filme = new Filme();
        BeanUtils.copyProperties(filmeRequest, filme, "dataCadastro", "genero");      
        return filme;
    }

    public static Filme convertResponse(FilmeResponse filmeResponse) {
        var filme = new Filme();
        BeanUtils.copyProperties(filmeResponse, filme);
        return filme;
    }
  
}
