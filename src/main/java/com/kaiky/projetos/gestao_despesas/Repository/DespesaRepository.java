package com.kaiky.projetos.gestao_despesas.Repository;

import com.kaiky.projetos.gestao_despesas.Model.DespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaModel, Long> {
}
