package com.tiagoperroni.movies.controller;

import java.util.List;

import com.tiagoperroni.movies.dto.FilmeRequest;
import com.tiagoperroni.movies.dto.FilmeResponse;
import com.tiagoperroni.movies.service.FilmeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public ResponseEntity<FilmeResponse> cadastrar(@RequestBody FilmeRequest filmeRequest) {
        return new ResponseEntity<>(this.filmeService.cadastrar(filmeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.filmeService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listarFilmes() {
        return new ResponseEntity<>(this.filmeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<FilmeResponse>> listarPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(this.filmeService.buscarPorNome(nome), HttpStatus.OK);
    }

    @GetMapping("/genero/{generoId}")
    public ResponseEntity<List<FilmeResponse>> listarPorNome(@PathVariable Long generoId) {
        return new ResponseEntity<>(this.filmeService.buscarPorCategoria(generoId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizarFilme(@PathVariable Long id, @RequestBody FilmeRequest filmeRequest) {
        return new ResponseEntity<>(this.filmeService.atualizarFilme(id, filmeRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        this.filmeService.deletarFilme(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
