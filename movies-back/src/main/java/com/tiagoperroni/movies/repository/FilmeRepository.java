package com.tiagoperroni.movies.repository;

import java.util.List;

import com.tiagoperroni.movies.model.Filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByNomeIgnoreCaseContaining(String nome);
    List<Filme> findByGeneroId(Long id);
 
}
