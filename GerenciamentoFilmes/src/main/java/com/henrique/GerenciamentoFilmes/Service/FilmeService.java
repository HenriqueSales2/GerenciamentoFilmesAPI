package com.henrique.GerenciamentoFilmes.Service;


import com.henrique.GerenciamentoFilmes.DTO.FilmeDTO;
import com.henrique.GerenciamentoFilmes.DTO.FilmeResponseDTO;
import com.henrique.GerenciamentoFilmes.Model.Filme;
import com.henrique.GerenciamentoFilmes.Model.FilmeGenero;
import com.henrique.GerenciamentoFilmes.Model.Genero;
import com.henrique.GerenciamentoFilmes.Repository.IFilmesRepository;
import com.henrique.GerenciamentoFilmes.Repository.IGeneroRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    @Autowired // injeção de dependencia, como se fosse um construtor
    private IFilmesRepository filmesRepository;

    @Autowired // injeção de dependencia, como se fosse um construtor
    private IGeneroRepository generoRepository;

    public Filme criarFilme(@NotNull FilmeDTO filmeDTO) {

        // criar instancia da entidade Filme
        Filme filme = new Filme();

        // define o nome e ano do filme om base no DTO recebido
        filme.setNomeFilme(filmeDTO.nomeFilme());
        filme.setAnoFilme(filmeDTO.anoLancamento());
        Filme filmeSalvo = filmesRepository.save(filme);

        for(Long idGenero : filmeDTO.idGenero()) {

            // expressão lambda
            Genero genero = generoRepository.findById(idGenero).
                    orElseThrow(() -> new EntityNotFoundException("Gênero com ID: " + idGenero + " não encontrado"));

            // criar uma nova instancia da entidade que está associado a filme
            FilmeGenero filmeGenero = new FilmeGenero();
            // define o filme associado
            filmeGenero.setFilme(filmeSalvo);
            // define o genero associado
            filmeGenero.setGenero(genero);

            // salvar a associacao à lista de gêneros de filmes
            filmeSalvo.getGeneros().add(filmeGenero);

        }
        return filmesRepository.save(filmeSalvo);
     }

    public List<FilmeResponseDTO> listarFilmes() {
        List<Filme> filmes = filmesRepository.findAll();

        return filmes.stream()
                .map(filme -> {
                    List<String> nomesGeneros = filme.getGeneros().stream()
                            .map(filmeGenero -> filmeGenero.getGenero().getNomeGenero())
                            .toList();

                    return new FilmeResponseDTO(
                            filme.getIdFilme(),
                            filme.getNomeFilme(),
                            filme.getAnoFilme(),
                            nomesGeneros
                    );
                })
                .toList();
    }

    public Filme atualizarFilme(@NotNull FilmeDTO filmeDTO) {
        Filme filmeEncontrado = filmesRepository.findById(filmeDTO.idFilme()).
                orElseThrow(() -> new EntityNotFoundException("Filme com ID: " + filmeDTO.idFilme() + " não encontrado"));

        filmeEncontrado.setNomeFilme(filmeDTO.nomeFilme());
        filmeEncontrado.setAnoFilme(filmeDTO.anoLancamento());

        filmeEncontrado.getGeneros().clear();

        List<FilmeGenero> novosGeneros = new ArrayList<>();

        for(Long idGenero : filmeDTO.idGenero()) {

            // expressão lambda
            Genero genero = generoRepository.findById(idGenero).
                    orElseThrow(() -> new EntityNotFoundException("Gênero com ID: " + idGenero + " não encontrado"));

            // criar uma nova instancia da entidade que está associado a filme
            FilmeGenero novoFilmeGenero = new FilmeGenero();
            // define o filme associado
            novoFilmeGenero.setFilme(filmeEncontrado);
            // define o genero associado
            novoFilmeGenero.setGenero(genero);

            // salvar a associacao à lista de gêneros de filmes
            filmeEncontrado.getGeneros().add(novoFilmeGenero);

        }
        return filmesRepository.save(filmeEncontrado);
    }

    public FilmeResponseDTO listarFilmesPorId(@PathVariable long idFilme) {
        
        Filme filme = filmesRepository.findById(idFilme)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

                List<String> nomesGeneros = filme.getGeneros().stream()
                        .map(filmeGenero -> filmeGenero.getGenero().getNomeGenero())
                        .toList();

                    return new FilmeResponseDTO(
                            filme.getIdFilme(),
                            filme.getNomeFilme(),
                            filme.getAnoFilme(),
                            nomesGeneros
                    );

    }
       



    public void deletarFilme(long idFilme) {
        Filme filmeEncontrado = filmesRepository.findById(idFilme)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum filme foi encontrado"));

        filmesRepository.delete(filmeEncontrado);

    }

    }

