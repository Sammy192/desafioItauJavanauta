package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.javanauta.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> transacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        log.info("Iniciado o processamento de gravar transações");
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que data e hora atual");
            throw new UnprocessableEntity("Data e hora maior que data e hora atual");
        }
        if(dto.valor() < 0) {
            log.error("Valor não pode ser negativo");
            throw new UnprocessableEntity("Valor não pode ser negativo");
        }
        transacoes.add(dto);
    }
}
