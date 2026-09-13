package com.kaiky.projetos.gestao_despesas.Controller;


import com.kaiky.projetos.gestao_despesas.DTO.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.DTO.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.Service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping("/todasDespesas")
    public ResponseEntity<List<DespesaResponseDTO>> viewAll(){
        List<DespesaResponseDTO> despesas = despesaService.viewAll();
        return ResponseEntity.status(HttpStatus.OK).body(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(despesaService.findById(id));
    }




    @PostMapping
    public ResponseEntity<DespesaResponseDTO> create(@Valid @RequestBody DespesaRequestDTO despesaRequest){
        DespesaResponseDTO despesaResponse = despesaService.create(despesaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaResponse);
    }



}
