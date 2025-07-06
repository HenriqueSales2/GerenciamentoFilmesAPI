package com.henrique.GerenciamentoFilmes.Repository;

import com.henrique.GerenciamentoFilmes.Model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneroRepository extends JpaRepository<Genero, Long> {
}
