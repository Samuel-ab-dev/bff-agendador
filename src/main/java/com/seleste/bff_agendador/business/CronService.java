package com.seleste.bff_agendador.business;

import com.seleste.bff_agendador.business.DTO.in.LoginDTORequest;
import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterParaRequest());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadas(horaFutura,
                horaFuturaMaisCinco, token);
        listaDeTarefas.forEach(tarefas -> {
            emailService.enviaEmail(tarefas);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefas.getId(), token);
        });
    }

    public String login(LoginDTORequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequest() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
