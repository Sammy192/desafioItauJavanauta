package com.javanauta.transacao_api.controllers;

import com.javanauta.transacao_api.business.services.TransacaoService;
import com.javanauta.transacao_api.controllers.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Adicionar novas transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos na transação"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na transação"  ),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.adicionarTransacoes(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Deletar todas as transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos na transação"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na transação"  ),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema")
    })
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limpaTransacoes();
        return ResponseEntity.ok().build();
    }
}
