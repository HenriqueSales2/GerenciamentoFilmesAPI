package com.henrique.GerenciamentoFilmes.DTO;

import java.util.List;

public record FilmeResponseDTO(
        long idFilme,
        String nomeFilme,
        Integer anoLancamento,
        List<String> generos
) {
}
