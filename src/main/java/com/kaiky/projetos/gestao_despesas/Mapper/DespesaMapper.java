package com.kaiky.projetos.gestao_despesas.Mapper;

import com.kaiky.projetos.gestao_despesas.DTO.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.DTO.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.Enum.TipoGasto;
import com.kaiky.projetos.gestao_despesas.Model.DespesaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DespesaMapper {

    public static DespesaResponseDTO ModelToResponse(DespesaModel despesa) {
        return new DespesaResponseDTO(
                despesa.getId(),
                despesa.getNome(),
                despesa.getMotivo(),
                despesa.getValor(),
                despesa.getTipo()
        );
    }


    public static DespesaModel RequestToModel(DespesaRequestDTO despesaRequest) {
        return new DespesaModel(
                despesaRequest.nome(),
                despesaRequest.motivo(),
                despesaRequest.valor(),
                TipoGasto.valueOf(despesaRequest.tipo())
        );
    }

    public static List<DespesaResponseDTO> ListModelToResponse(List<DespesaModel> despesaModelList) {
        return despesaModelList.stream()
                .map(despesaModel -> ModelToResponse(despesaModel))
                .collect(Collectors.toList());

    }


}