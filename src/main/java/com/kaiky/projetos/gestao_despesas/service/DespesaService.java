package com.kaiky.projetos.gestao_despesas.service;

import com.kaiky.projetos.gestao_despesas.dto.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.dto.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.exception.DespesaNotFoundException;
import com.kaiky.projetos.gestao_despesas.mapper.DespesaMapper;
import com.kaiky.projetos.gestao_despesas.model.DespesaModel;
import com.kaiky.projetos.gestao_despesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public DespesaResponseDTO create(DespesaRequestDTO DespesaRequest){
        DespesaModel despesa = DespesaMapper.requestToModel(DespesaRequest);
        despesa = despesaRepository.save(despesa);
        return DespesaMapper.modelToResponse(despesa);
    }

    public DespesaResponseDTO findById(Long id){
        Optional<DespesaModel> despesa = despesaRepository.findById(id);

        if(despesa.isEmpty()){
            throw new DespesaNotFoundException();
        }

        return DespesaMapper.modelToResponse(despesa.get());
    }




    public List<DespesaResponseDTO> viewAll(){
        List<DespesaModel> despesas = despesaRepository.findAll();
        return DespesaMapper.ListModelToResponse(despesas);
    }


}
