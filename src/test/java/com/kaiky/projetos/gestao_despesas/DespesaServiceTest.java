package com.kaiky.projetos.gestao_despesas;


import com.kaiky.projetos.gestao_despesas.dto.DespesaRequestDTO;
import com.kaiky.projetos.gestao_despesas.dto.DespesaResponseDTO;
import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;
import com.kaiky.projetos.gestao_despesas.model.DespesaModel;
import com.kaiky.projetos.gestao_despesas.repository.DespesaRepository;
import com.kaiky.projetos.gestao_despesas.service.DespesaService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
    
}
