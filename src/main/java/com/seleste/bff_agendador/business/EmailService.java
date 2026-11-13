package com.seleste.bff_agendador.business;

import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.infrastructure.security.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class EmailService {

    private EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}
