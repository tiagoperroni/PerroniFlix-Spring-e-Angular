package com.tiagoperroni.movies.controller;

import java.util.List;

import com.tiagoperroni.movies.dto.GeneroRequest;
import com.tiagoperroni.movies.dto.GeneroResponse;
import com.tiagoperroni.movies.service.GeneroService;

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
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroResponse> cadastrar(@RequestBody GeneroRequest generoRequest) {
        return new ResponseEntity<>(this.generoService.cadastrar(generoRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GeneroResponse>> listarFilmes() {
        return new ResponseEntity<>(this.generoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponse> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.generoService.buscarPorIdResponse(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<GeneroResponse>> buscarPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(this.generoService.buscarPorNome(nome), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponse> atualizarGenero(@PathVariable Long id,
            @RequestBody GeneroRequest generoRequest) {
        return new ResponseEntity<>(this.generoService.atualizarGenero(id, generoRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarGenero(@PathVariable Long id) {
        return new ResponseEntity<>(this.generoService.deletarGenero(id), HttpStatus.ACCEPTED);
    }

}
