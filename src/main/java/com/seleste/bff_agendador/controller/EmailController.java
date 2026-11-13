package com.seleste.bff_agendador.controller;

import com.seleste.bff_agendador.business.DTO.in.TarefasDTORequest;
import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.business.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;

    @PostMapping
    @Operation(summary = "Enviar Email", description = "Envia email de tarefas ao usuário.")
    @ApiResponse(responseCode = "200", description = "Email enviado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Falha ao enviar email.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTOResponse tarefasDTO) {
        emailService.enviaEmail(tarefasDTO);
        return ResponseEntity.ok().build();
    }
}
