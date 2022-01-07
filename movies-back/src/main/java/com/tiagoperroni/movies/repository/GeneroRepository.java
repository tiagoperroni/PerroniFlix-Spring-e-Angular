package com.tiagoperroni.movies.repository;

import java.util.List;

import com.tiagoperroni.movies.model.Genero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{
    
    List<Genero> findByNomeIgnoreCaseContaining(String nome);

}
