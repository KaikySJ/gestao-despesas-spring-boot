package com.kaiky.projetos.gestao_despesas.DTO;

import com.kaiky.projetos.gestao_despesas.Enum.TipoGasto;
import com.kaiky.projetos.gestao_despesas.Validation.ValueOfEnum;
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
