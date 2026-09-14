package com.kaiky.projetos.gestao_despesas.repository;

import com.kaiky.projetos.gestao_despesas.model.DespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaModel, Long> {
}
