package com.seleste.bff_agendador.business.DTO.out;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTOResponse {
    private String numero;
    private String ddd;
}
