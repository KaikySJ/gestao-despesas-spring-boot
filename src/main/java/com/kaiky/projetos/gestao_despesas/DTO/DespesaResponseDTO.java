package com.kaiky.projetos.gestao_despesas.DTO;

import com.kaiky.projetos.gestao_despesas.Enum.TipoGasto;


public record DespesaResponseDTO(
        Long id,
        String nome,
        String motivo,
        double valor,
        TipoGasto tipo
) {

}
