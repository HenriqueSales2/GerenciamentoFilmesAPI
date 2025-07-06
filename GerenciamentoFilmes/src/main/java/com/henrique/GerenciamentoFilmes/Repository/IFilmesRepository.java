package com.henrique.GerenciamentoFilmes.Repository;

import com.henrique.GerenciamentoFilmes.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFilmesRepository extends JpaRepository<Filme, Long> {
}
