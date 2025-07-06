package com.henrique.GerenciamentoFilmes.Controller;

import com.henrique.GerenciamentoFilmes.Model.FilmeGenero;
import com.henrique.GerenciamentoFilmes.Model.Genero;
import com.henrique.GerenciamentoFilmes.Service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/genero")
@CrossOrigin("*")

public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @PostMapping
    @Operation(summary = "Cadastrar Gêneros: ", description = "Endpoint para cadastrar gêneros")
    public ResponseEntity<Genero> cadastrarGenero(@RequestBody Genero genero){
        return new ResponseEntity<>(generoService.criarGenero(genero), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar Gêneros: ", description = "Endpoint para listar gêneros")
    public ResponseEntity<List<Genero>> listarGenero(){
        List<Genero> listaGeneros = generoService.listarGenero();
        if(listaGeneros.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaGeneros, HttpStatus.OK);
    }

    @DeleteMapping("/{idGenero}")
    @Operation(summary = "Deletar Gênero: ", description = "Endpoint para deletar gêneros")
    public ResponseEntity<Void> deletarGenero(@PathVariable long idGenero){
        generoService.deletarGenero(idGenero);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
