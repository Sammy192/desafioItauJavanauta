package com.javanauta.transacao_api.controllers.dtos;

public record EstatisticasResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
        ) {
}
