package com.kaiky.projetos.gestao_despesas.dto;

import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;


public record DespesaResponseDTO(
        Long id,
        String nome,
        String motivo,
        double valor,
        TipoGasto tipo
) {

}
