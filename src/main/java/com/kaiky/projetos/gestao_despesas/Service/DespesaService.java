package com.kaiky.projetos.gestao_despesas.Service;

import com.kaiky.projetos.gestao_despesas.DTO.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.DTO.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.Exception.DespesaNotFoundException;
import com.kaiky.projetos.gestao_despesas.Mapper.DespesaMapper;
import com.kaiky.projetos.gestao_despesas.Model.DespesaModel;
import com.kaiky.projetos.gestao_despesas.Repository.DespesaRepository;
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
        DespesaModel despesa = DespesaMapper.RequestToModel(DespesaRequest);
        despesa = despesaRepository.save(despesa);
        return DespesaMapper.ModelToResponse(despesa);
    }

    public DespesaResponseDTO findById(Long id){
        Optional<DespesaModel> despesa = despesaRepository.findById(id);

        if(despesa.isEmpty()){
            throw new DespesaNotFoundException();
        }

        return DespesaMapper.ModelToResponse(despesa.get());
    }




    public List<DespesaResponseDTO> viewAll(){
        List<DespesaModel> despesas = despesaRepository.findAll();
        return DespesaMapper.ListModelToResponse(despesas);
    }


}
