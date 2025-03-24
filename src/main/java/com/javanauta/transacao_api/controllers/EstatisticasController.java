package com.javanauta.transacao_api.controllers;

import com.javanauta.transacao_api.business.services.EstatisticasService;
import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "tempoInvervaloBusca", required = false, defaultValue = "60") Integer tempoInvervaloBusca) {
        EstatisticasResponseDTO estatisticasResponseDTO = estatisticasService.calculaEstatisticasTransacoes(tempoInvervaloBusca);
        return ResponseEntity.ok(estatisticasResponseDTO);
    }
}
