package com.henrique.GerenciamentoFilmes.Service;

import com.henrique.GerenciamentoFilmes.DTO.FilmeDTO;
import com.henrique.GerenciamentoFilmes.Model.Genero;
import com.henrique.GerenciamentoFilmes.Repository.IFilmesRepository;
import com.henrique.GerenciamentoFilmes.Repository.IGeneroRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private IGeneroRepository generoRepository;

    public Genero criarGenero(@NotNull Genero generoDTO) {
        return  generoRepository.save(generoDTO);
    }

    public List<Genero> listarGenero() {

        return generoRepository.findAll();
    }

    public void deletarGenero(long idGenero) {
        Genero genero = generoRepository.findById(idGenero).
                orElseThrow(() -> new EntityNotFoundException("Nenhum Genero encontrado"));

        generoRepository.delete(genero);

    }


}
