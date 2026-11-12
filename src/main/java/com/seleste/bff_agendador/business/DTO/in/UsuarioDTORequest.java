package com.seleste.bff_agendador.business.DTO.in;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTORequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> endereco;
    private List<TelefoneDTORequest> telefone;
}
