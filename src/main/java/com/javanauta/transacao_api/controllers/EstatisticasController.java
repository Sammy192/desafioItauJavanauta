package com.javanauta.transacao_api.controllers;

import com.javanauta.transacao_api.business.services.EstatisticasService;
import com.javanauta.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Buscar estatísticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos na transação"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"  ),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "tempoInvervaloBusca", required = false, defaultValue = "60") Integer tempoInvervaloBusca) {
        EstatisticasResponseDTO estatisticasResponseDTO = estatisticasService.calculaEstatisticasTransacoes(tempoInvervaloBusca);
        return ResponseEntity.ok(estatisticasResponseDTO);
    }
}
