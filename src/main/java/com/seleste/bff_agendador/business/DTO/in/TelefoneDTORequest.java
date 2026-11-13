package com.seleste.bff_agendador.business.DTO.in;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTORequest {
    private String numero;
    private String ddd;
}
