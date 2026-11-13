package com.seleste.bff_agendador.infrastructure.security.client.config;

import com.seleste.bff_agendador.infrastructure.exceptions.BusinessException;
import com.seleste.bff_agendador.infrastructure.exceptions.ConflictException;
import com.seleste.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.seleste.bff_agendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
   public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409:
                return new ConflictException("Atributo já existente");
            case 403:
                return new ResourceNotFoundException("Atributo não encontrado");
            case 401:
                return new UnauthorizedException("Usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
