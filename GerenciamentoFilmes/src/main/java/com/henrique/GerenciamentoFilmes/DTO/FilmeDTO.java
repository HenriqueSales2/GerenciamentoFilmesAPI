package com.henrique.GerenciamentoFilmes.DTO;

import com.henrique.GerenciamentoFilmes.Model.FilmeGenero;

import java.util.List;

public record FilmeDTO(
        long idFilme,
        String nomeFilme,
        Integer anoLancamento,
        List<Long> idGenero
) {
}
