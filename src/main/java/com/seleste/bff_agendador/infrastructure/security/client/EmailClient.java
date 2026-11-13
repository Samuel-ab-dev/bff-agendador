package com.seleste.bff_agendador.infrastructure.security.client;

import com.seleste.bff_agendador.business.DTO.in.*;
import com.seleste.bff_agendador.business.DTO.out.EnderecoDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.TelefoneDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping

    void enviarEmail(@RequestBody TarefasDTOResponse tarefasDTO);
}
