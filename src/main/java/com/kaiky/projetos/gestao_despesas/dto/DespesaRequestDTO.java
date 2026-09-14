package com.kaiky.projetos.gestao_despesas.dto;

import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;
import com.kaiky.projetos.gestao_despesas.validation.ValueOfEnum;
import jakarta.validation.constraints.*;

public record DespesaRequestDTO(
        @NotBlank(message = "Name must not be empty")
        String nome,

        @NotBlank
        String motivo,

        @Positive
        double valor,

        @NotNull
        @ValueOfEnum(enumClass = TipoGasto.class, message = "Status não válido")
        String tipo
) {
}
