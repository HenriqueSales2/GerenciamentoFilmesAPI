package com.henrique.GerenciamentoFilmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "filme") // mudar o nome da tabela no SQL
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFilme;

    @NotBlank(message = "O nome do filme é obrigatório!")
    private String nomeFilme;

    @NotBlank(message = "O ano de lançamento é obrigatório!")
    @Min(value = 1888, message = "Ano de lançamento inválido!")
    private Integer anoFilme;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<FilmeGenero> generos  = new ArrayList<>();

}
