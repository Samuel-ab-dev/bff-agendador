package com.seleste.bff_agendador.business.DTO.out;

import com.seleste.bff_agendador.business.DTO.in.EnderecoDTORequest;
import com.seleste.bff_agendador.business.DTO.in.TelefoneDTORequest;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTOResponse {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> endereco;
    private List<TelefoneDTORequest> telefone;
}
