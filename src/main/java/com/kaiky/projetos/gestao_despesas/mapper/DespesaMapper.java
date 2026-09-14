package com.kaiky.projetos.gestao_despesas.mapper;

import com.kaiky.projetos.gestao_despesas.dto.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.dto.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;
import com.kaiky.projetos.gestao_despesas.model.DespesaModel;

import java.util.List;
import java.util.stream.Collectors;

public class DespesaMapper {

    public static DespesaResponseDTO modelToResponse(DespesaModel despesa) {
        return new DespesaResponseDTO(
                despesa.getId(),
                despesa.getNome(),
                despesa.getMotivo(),
                despesa.getValor(),
                despesa.getTipo()
        );
    }


    public static DespesaModel requestToModel(DespesaRequestDTO despesaRequest) {
        return new DespesaModel(
                despesaRequest.nome(),
                despesaRequest.motivo(),
                despesaRequest.valor(),
                TipoGasto.valueOf(despesaRequest.tipo())
        );
    }

    public static List<DespesaResponseDTO> listModelToResponse(List<DespesaModel> despesaModelList) {
        return despesaModelList.stream()
                .map(despesaModel -> modelToResponse(despesaModel))
                .collect(Collectors.toList());

    }


}