package com.seleste.bff_agendador.controller;

import com.seleste.bff_agendador.business.DTO.in.TarefasDTORequest;
import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.business.TarefasService;
import com.seleste.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import com.seleste.bff_agendador.infrastructure.security.configs.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Métodos das tarefas.")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salva Tarefas", description = "Cria uma nova tarefa.")
    @ApiResponse(responseCode = "200", description = "Tarefa(s) salva(s) com sucesso.")
    @ApiResponse(responseCode = "400", description = "Tarefa(s) já cadastrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                                           @RequestHeader(value = "Authorization",
                                                            required = false)
                                                    String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Dados de Lista de Tarefas por Data",
            description = "Busca os dados das Tarefas por período de tempo.")
    @ApiResponse(responseCode = "200", description = "Tarefa(s) encontrada(s).")
    @ApiResponse(responseCode = "404", description = "Tarefa(s) não encontrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListDeTarefas(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadas(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca Dados de Lista de Tarefas por Email",
            description = "Busca os dados das Tarefas por email do usuário.")
    @ApiResponse(responseCode = "200", description = "Tarefa(s) encontrada(s).")
    @ApiResponse(responseCode = "404", description = "Tarefa(s) não encontrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(value = "Authorization",
            required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaEmailUsuario(token));
    }

    @PatchMapping
    @Operation(summary = "Altera Status de Lista de Tarefas",
            description = "Altera status da notificação das tarefas.")
    @ApiResponse(responseCode = "200", description = "Status atualizado(s).")
    @ApiResponse(responseCode = "404", description = "Tarefa(s) não encontrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<TarefasDTOResponse> alteraStatusDeNotificacao(@RequestParam("status")
                                                                StatusNotificacaoEnum status,
                                                                        @RequestParam("id") String id,
                                                                        @RequestHeader(value = "Authorization",
                                                                        required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza Tarefas",
            description = "Atualiza os dados das tarefas do usuário.")
    @ApiResponse(responseCode = "200", description = "Tarefas atualizada(s).")
    @ApiResponse(responseCode = "404", description = "Tarefa(s) não encontrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(value = "Authorization",
                                                            required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id, token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Dados de Lista de Tarefas",
            description = "Deleta dados de listas de tarefas através do id.")
    @ApiResponse(responseCode = "200", description = "Tarefa(s) deletada(s).")
    @ApiResponse(responseCode = "404", description = "Tarefa(s) não encontrada(s).")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(value = "Authorization",
                                                          required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }
}
