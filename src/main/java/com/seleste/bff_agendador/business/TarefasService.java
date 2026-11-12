package com.seleste.bff_agendador.business;

import com.seleste.bff_agendador.business.DTO.in.TarefasDTORequest;
import com.seleste.bff_agendador.business.DTO.out.TarefasDTOResponse;
import com.seleste.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import com.seleste.bff_agendador.infrastructure.security.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTO) {
        return client.gravarTarefas(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadas(LocalDateTime dataInicial,
                                                         LocalDateTime dataFinal, String token) {
        return client.buscaListDeTarefas(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaEmailUsuario(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return client.alteraStatusDeNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest tarefasDTO, String id, String token) {
        return client.updateTarefas(tarefasDTO, id, token);
    }
}
