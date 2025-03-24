package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calculaEstatisticasTransacoes(Integer tempoInvervaloBusca) {
        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoesPorIntervalo(tempoInvervaloBusca);

        DoubleSummaryStatistics estatisticas = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        return new EstatisticasResponseDTO(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
