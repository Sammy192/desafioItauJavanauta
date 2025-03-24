package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.javanauta.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticasResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstatisticasResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void buscaTransacoesPorIntervaloDeveAdicionarTransacaoComSucesso(){

        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoesPorIntervalo(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void buscaTransacoesPorIntervaloDeveLancarExcecaoCasoValorSejaNegativo(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não pode ser negativo", exception.getMessage());
    }

    @Test
    void buscaTransacoesPorIntervaloDeveLancarExcecaoCasoDataOuHoraMaiorQueAtual(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora maior que data e hora atual", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.limpaTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoesPorIntervalo(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacaoDentroDoIntervalo(){

        TransacaoRequestDTO transacaoForaDoIntervalo = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(transacaoForaDoIntervalo);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoesPorIntervalo(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(transacaoForaDoIntervalo));
    }

}
