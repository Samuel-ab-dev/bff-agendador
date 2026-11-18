package com.seleste.bff_agendador.infrastructure.security.client;

import com.seleste.bff_agendador.business.DTO.in.EnderecoDTORequest;
import com.seleste.bff_agendador.business.DTO.in.LoginDTORequest;
import com.seleste.bff_agendador.business.DTO.in.TelefoneDTORequest;
import com.seleste.bff_agendador.business.DTO.in.UsuarioDTORequest;
import com.seleste.bff_agendador.business.DTO.out.CepDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.EnderecoDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.TelefoneDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}", path = "/usuario")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTO);

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                           @RequestHeader("Authorization") String token);
    @DeleteMapping("/{email}")
    Void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                      @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization")
                                 String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization")
                                 String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                        @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    CepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);

}
