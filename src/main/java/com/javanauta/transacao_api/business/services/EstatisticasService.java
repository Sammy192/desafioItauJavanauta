package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calculaEstatisticasTransacoes(Integer tempoInvervaloBusca) {
        log.info("Iniciado calculaEstatisticasTransacoes buscando invertavlo: {}", tempoInvervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoesPorIntervalo(tempoInvervaloBusca);
        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        return new EstatisticasResponseDTO(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
