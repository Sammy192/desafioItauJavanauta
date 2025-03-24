package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {

    @Mock
    TransacaoService transacaoService;

    @InjectMocks
    EstatisticasService estatisticasService;

    private TransacaoRequestDTO transacaoRequestDTO;

    private EstatisticasResponseDTO estatisticasResponseDTO;
    private final Integer TEMPO_INTERVALO_SEGUNDOS = 60;

    @BeforeEach
    void setUp() {
        transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticasResponseDTO = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calculaEstatisticasTransacoesDeveCalcularEstatisticasComSucesso(){
        //import org.assertj.core.api.Assertions;
        when(transacaoService.buscaTransacoesPorIntervalo(60))
                .thenReturn(List.of(transacaoRequestDTO));
        //Collections.singletonList(transacaoRequestDTO)

        EstatisticasResponseDTO resultado = estatisticasService.calculaEstatisticasTransacoes(TEMPO_INTERVALO_SEGUNDOS);

        verify(transacaoService, times(1)).buscaTransacoesPorIntervalo(60);
        //comparar mais detalhado para objetos complexos
        //Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticasResponseDTO);
        Assertions.assertEquals(estatisticasResponseDTO, resultado);

    }

    @Test
    void calculaEstatisticasTransacoesDeveRetornarEstatisticaZeradasQuandoListaVazia(){
        //import org.assertj.core.api.Assertions;
        EstatisticasResponseDTO estatisticaEsperado =
                new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);

        when(transacaoService.buscaTransacoesPorIntervalo(TEMPO_INTERVALO_SEGUNDOS))
                .thenReturn(Collections.emptyList());

        EstatisticasResponseDTO resultado = estatisticasService.calculaEstatisticasTransacoes(TEMPO_INTERVALO_SEGUNDOS);

        verify(transacaoService, times(1)).buscaTransacoesPorIntervalo(TEMPO_INTERVALO_SEGUNDOS);
        //comparar mais detalhado para objetos complexos
        //Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperado);
        Assertions.assertEquals(estatisticaEsperado, resultado);
    }
}
