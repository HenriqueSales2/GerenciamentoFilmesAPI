package com.henrique.GerenciamentoFilmes.Controller;


import com.henrique.GerenciamentoFilmes.DTO.FilmeDTO;
import com.henrique.GerenciamentoFilmes.DTO.FilmeResponseDTO;
import com.henrique.GerenciamentoFilmes.Model.Filme;
import com.henrique.GerenciamentoFilmes.Service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
@CrossOrigin("*")
public class FilmeController {

    @Autowired // serve para trazer todos os métodos do Service
    private FilmeService filmeService;

    @PostMapping
    @Operation(summary = "Cadastrar Filmes:  ", description = "Endpoint para cadastrar filmes")
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody FilmeDTO filmeDTO) {
        return new ResponseEntity<>(filmeService.criarFilme(filmeDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar Filmes: ", description = "Endpoint para listar filmes")
    public ResponseEntity<List<FilmeResponseDTO>> listarFilmes() {
        List<FilmeResponseDTO> filmesDTO = filmeService.listarFilmes();

        if(filmesDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        return ResponseEntity.ok(filmesDTO);
        return new ResponseEntity<>(filmesDTO, HttpStatus.OK);
    }

    @PutMapping("/{idFilme}")
    @Operation(summary = "Atualizar Filme: ", description = "Endpoint para atualizar filmes")
    public ResponseEntity<Filme> atualizarFilme(@Valid @RequestBody FilmeDTO filmeDTO) {
        Filme filmeAtualizado = filmeService.atualizarFilme(filmeDTO);
        return ResponseEntity.ok(filmeAtualizado);
    }

    @GetMapping("{idFilme}")
    @Operation(summary = "Buscar Filme Por Id: ", description = "Endpoint para buscar filme por id")
    public ResponseEntity<FilmeResponseDTO> buscarFilmePorId(@PathVariable long idFilme) {
        FilmeResponseDTO filme = filmeService.listarFilmesPorId(idFilme);
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    @DeleteMapping("/{idFilme}")
    @Operation(summary = "Deletar Filme Por Id: ", description = "Endpoint para deletar filmes")
    public ResponseEntity<Void> deletarFilme(@PathVariable long idFilme) {
        filmeService.deletarFilme(idFilme);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
