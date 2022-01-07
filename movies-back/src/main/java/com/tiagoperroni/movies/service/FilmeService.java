package com.tiagoperroni.movies.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tiagoperroni.movies.dto.FilmeRequest;
import com.tiagoperroni.movies.dto.FilmeResponse;
import com.tiagoperroni.movies.exceptions.ObjetoNaoEncontradoException;
import com.tiagoperroni.movies.exceptions.ValidacaoDeCamposException;
import com.tiagoperroni.movies.model.Filme;
import com.tiagoperroni.movies.model.Genero;
import com.tiagoperroni.movies.repository.FilmeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    GeneroService generoService;

    public FilmeResponse cadastrar(FilmeRequest filmeRequest) {
        validaCadastro(filmeRequest);
        var filme = new Filme();
        filme.setGenero(this.findGenero(filmeRequest.getGenero()));
        BeanUtils.copyProperties(filmeRequest, filme, "genero");
        return FilmeResponse.convertResponse(this.filmeRepository.save(filme));

    }

    public FilmeResponse buscarPorId(Long id) {
        return FilmeResponse.convertResponse(this.filmeRepository.findById(id).orElseThrow(
                () -> new ObjetoNaoEncontradoException(String.format("O Filme de id %s não foi encontrado.", id))));
    }

    public List<FilmeResponse> getAll() {
        return this.filmeRepository.findAll().stream()
                .map(FilmeResponse::convertResponse).collect(Collectors.toList());
    }

    public List<FilmeResponse> buscarPorNome(String nome) {
        return this.filmeRepository.findByNomeIgnoreCaseContaining(nome).stream()
                .map(filme -> FilmeResponse.convertResponse(filme))
                .collect(Collectors.toList());
    }

    public List<FilmeResponse> buscarPorCategoria(Long id) {
        return this.filmeRepository.findByGeneroId(id).stream()
                .map(filme -> FilmeResponse.convertResponse(filme))
                .collect(Collectors.toList());
    }

    public FilmeResponse atualizarFilme(Long id, FilmeRequest filmeRequest) {
        this.validaCadastro(filmeRequest);
        var filme = Filme.convertResponse(this.buscarPorId(id));        
        filme.setGenero(this.findGenero(filmeRequest.getGenero()));
        BeanUtils.copyProperties(filmeRequest, filme, "dataCadastro");       
        return FilmeResponse.convertResponse(this.filmeRepository.save(filme));
    }

    public void deletarFilme(Long id) {
        this.buscarPorId(id);
        this.filmeRepository.deleteById(id);        
    }

    public void validaCadastro(FilmeRequest filme) {
        if (filme.getNome() == null) {
            throw new ValidacaoDeCamposException("O nome precisa ser preenchido");
        }
        if (filme.getGenero() == null) {
            throw new ValidacaoDeCamposException("O genero precisa ser preenchido");
        }
        if (filme.getImageUrl() == null) {
            throw new ValidacaoDeCamposException("O link da imagem precisa ser preenchido");
        }
        if (filme.getDescricao() == null) {
            throw new ValidacaoDeCamposException("A descrição precisa ser preenchida");
        }
        if (filme.getSinopse() == null) {
            throw new ValidacaoDeCamposException("A sinopse precisa ser preenchida");
        }
    }

    public Genero findGenero(Long id) {
        return this.generoService.buscarPorId(id);
    }

}
