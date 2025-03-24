package com.javanauta.transacao_api.controllers.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
       Double valor,
       OffsetDateTime dataHora
) {
}
