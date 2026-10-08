package com.kaiky.projetos.gestao_despesas;


import com.kaiky.projetos.gestao_despesas.dto.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.dto.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;
import com.kaiky.projetos.gestao_despesas.exception.DespesaNotFoundException;
import com.kaiky.projetos.gestao_despesas.model.DespesaModel;
import com.kaiky.projetos.gestao_despesas.repository.DespesaRepository;
import com.kaiky.projetos.gestao_despesas.service.DespesaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DespesaServiceTest {

    @Mock
    DespesaRepository despesaRepository;

    @InjectMocks
    DespesaService despesaService;

    @Nested
    class createDespesa {

        @Test
        void shouldCreateADespesa(){

            //Arrange - Dados que serão usados no teste

            DespesaRequestDTO despesaRequest =  new DespesaRequestDTO(
                    "Barra de chocolato",
                    "Estava com vontade",
                    7.99,
                    TipoGasto.COMIDA.toString()
                    );

           when(despesaRepository.save(any())).thenReturn(new DespesaModel(1, despesaRequest.nome(), despesaRequest.motivo(), despesaRequest.valor(), TipoGasto.valueOf(despesaRequest.tipo())));


            //Act

            DespesaResponseDTO output= despesaService.create(despesaRequest);

            //Assert
            assertNotNull(output);
        }
    }

      @Nested
    class findDespesaById {

        @Test
        @DisplayName("Should return a existing despesa from the database with ID 1")
        void shouldFindDespesaByIdSucessufully(){
            //Arrange
            DespesaModel storedDespesa = new DespesaModel(1, "Despesa", "Queria gastar", 20 , TipoGasto.COMIDA);
            when(despesaRepository.findById(1L)).thenReturn(Optional.of(storedDespesa));

            //Act
            DespesaResponseDTO output = despesaService.findById(1L);
            //Assert
            assertAll(
                    () -> assertEquals(storedDespesa.getNome(), output.nome()),
                    () -> assertEquals(storedDespesa.getMotivo(), output.motivo()),
                    () -> assertEquals(storedDespesa.getValor(), output.valor()),
                    () -> assertEquals(storedDespesa.getTipo(), output.tipo())
            );
        }


      @Test
      void shouldNotFindDespesaById(){
            when(despesaRepository.findById(1L)).thenReturn(Optional.empty());
            assertThrows(DespesaNotFoundException.class, () -> despesaService.findById(1L), "Should be returning DespesaNotFoundException because no despesa of ID 1 exists");
      }

    }

    @Nested
    class ViewAllDespesas {

        @Test
        @DisplayName("Should return a list containing two Despesas")
        void shouldReturnAListOfDespesasResponseSuccessfully() {
            //ARRANGE
            List<DespesaModel> listOfAllDespesas = new ArrayList<>();
            listOfAllDespesas.add(new DespesaModel(1L,
                    "Barra de chocolate",
                    "Estou sem comer doce a bastante tempo",
                    6,
                    TipoGasto.COMIDA));
            listOfAllDespesas.add(new DespesaModel(2L,
                    "Energético",
                    "Estou muito cansado",
                    8,
                    TipoGasto.COMIDA));

            List<DespesaResponseDTO> listOfExpectedResult = new ArrayList<>();
            listOfExpectedResult.add(new DespesaResponseDTO(1L,
                    "Barra de chocolate",
                    "Estou sem comer doce a bastante tempo",
                    6,
                    TipoGasto.COMIDA));
            listOfExpectedResult.add(new DespesaResponseDTO(2L,
                    "Energético",
                    "Estou muito cansado",
                    8,
                    TipoGasto.COMIDA));

            when(despesaRepository.findAll()).thenReturn(listOfAllDespesas);


            //ACT
            List<DespesaResponseDTO> output = despesaService.viewAll();

            //ASSERT
            assertNotNull(output);
            assertEquals(2, output.size());
            assertEquals(listOfExpectedResult, output);
        }

        @Test
        @DisplayName("Should return an empty list of DespesaResponseDTO")
        void shouldReturnAnEmptyList(){
            //Arrange
            List<DespesaResponseDTO> expectedResult = new ArrayList<>();
            List<DespesaModel> savedDespesas = new ArrayList<>();
            when(despesaRepository.findAll()).thenReturn(savedDespesas);
            //ACT
            List<DespesaResponseDTO> output = despesaService.viewAll();
            //ASSERT
            assertNotNull(output);
            assertEquals(expectedResult, output);

        }
    }

    @Nested
    class DeleteDespesa{

        @Test
        void shouldDeleteDespesaSuccessfully(){
            DespesaModel existingDespesa = new DespesaModel(1,"Barra de chocolate",
                    "Vontade",
                    7,
                    TipoGasto.COMIDA);

            when(despesaRepository.findById(1L)).thenReturn(Optional.of(existingDespesa));

            despesaService.delete(1L);

            verify(despesaRepository).delete(existingDespesa);
        }

        @Test
        @DisplayName("Should not delete a despesa because despesa doesnt exist")
        void shouldNotFindADespesaToDelete(){
            //ARRANGE
            when(despesaRepository.findById(any(Long.class))).thenThrow(DespesaNotFoundException.class);

            //ACT AND ASSERT
            assertThrows(DespesaNotFoundException.class, () -> despesaService.delete(1L), "Should be throwing despesaNotFoundException because theres no despesa with that ID");



        }


    }
    
}
