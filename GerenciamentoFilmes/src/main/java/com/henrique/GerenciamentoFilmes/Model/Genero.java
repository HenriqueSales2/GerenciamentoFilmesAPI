package com.henrique.GerenciamentoFilmes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    @NotBlank(message = "O nome do campo é obrigatório")
    private String nomeGenero;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonIgnore
    private List<FilmeGenero> filmeGeneros = new ArrayList<>();

}
