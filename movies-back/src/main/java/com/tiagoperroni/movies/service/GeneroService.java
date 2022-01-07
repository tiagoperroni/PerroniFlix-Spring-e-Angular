package com.tiagoperroni.movies.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import com.tiagoperroni.movies.dto.GeneroRequest;
import com.tiagoperroni.movies.dto.GeneroResponse;
import com.tiagoperroni.movies.exceptions.ExisteCadastroGeneroException;
import com.tiagoperroni.movies.exceptions.ObjetoNaoEncontradoException;
import com.tiagoperroni.movies.model.Genero;
import com.tiagoperroni.movies.repository.GeneroRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private FilmeService filmeService;

    public GeneroResponse cadastrar(GeneroRequest generoRequest) {
        this.validar(generoRequest);
        return GeneroResponse.convert(this.generoRepository.save(Genero.convert(generoRequest)));

    }

    public List<GeneroResponse> getAll() {
        return this.generoRepository.findAll().stream().map(x -> GeneroResponse.convert(x))
                .collect(Collectors.toList());
    }

    public Genero buscarPorId(Long id) {
        return this.generoRepository.findById(id).orElseThrow(
                () -> new ObjetoNaoEncontradoException(String.format("O genero com id: %s não foi encontrado", id)));
    }

    public GeneroResponse buscarPorIdResponse(Long id) {
        return GeneroResponse.convert(this.buscarPorId(id));

    }

    public List<GeneroResponse> buscarPorNome(String nome) {
        return this.generoRepository.findByNomeIgnoreCaseContaining(nome).stream().map(GeneroResponse::convert)
                .collect(Collectors.toList());
    }

    public GeneroResponse atualizarGenero(Long id, GeneroRequest generoRequest) {
        var genero = this.buscarPorId(id);
        BeanUtils.copyProperties(generoRequest, genero, "dataCadastro");
        this.generoRepository.save(genero);
        return GeneroResponse.convert(genero);
    }

    public String deletarGenero(Long id) {        
        this.validaExisteCadastro(id);
        this.generoRepository.deleteById(id);
        return "Genero deletado com sucesso;";
    }

    public void validar(GeneroRequest generoRequest) {
        if (generoRequest.getNome().isEmpty()) {
            throw new ValidationException("O campo nome é requerido.");
        }
    }

    public void validaExisteCadastro(Long id) {
        this.buscarPorId(id);      
        if (this.filmeService.buscarPorCategoria(id).size() != 0) {
            throw new ExisteCadastroGeneroException("Genero não pode ser deletado pois existem filmes cadastrados.");
        }
    }

}
