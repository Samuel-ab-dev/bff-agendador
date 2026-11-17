package com.seleste.bff_agendador.infrastructure.security.client.config;

import com.seleste.bff_agendador.infrastructure.exceptions.BusinessException;
import com.seleste.bff_agendador.infrastructure.exceptions.ConflictException;
import com.seleste.bff_agendador.infrastructure.exceptions.IllegalArgumentException;
import com.seleste.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.seleste.bff_agendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
   public Exception decode(String s, Response response) {

        String mensagemErr = mensagemErr(response);

        return switch (response.status()) {
            case 409 -> new ConflictException("Erro: " + mensagemErr);
            case 403 -> new ResourceNotFoundException("Erro: " + mensagemErr);
            case 401 -> new UnauthorizedException("Erro: " + mensagemErr);
            case 400 -> new IllegalArgumentException("Erro: " + mensagemErr);
            default -> new BusinessException("Erro de servidor");
        };
    }
   private String mensagemErr(Response res) {
       try {
           if(Objects.isNull(res.body())) {
               return "";
           }
           return new String(res.body()
                  .asInputStream()
                  .readAllBytes(), StandardCharsets.UTF_8);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
}
