package com.seleste.bff_agendador.controller;

import com.seleste.bff_agendador.business.DTO.in.EnderecoDTORequest;
import com.seleste.bff_agendador.business.DTO.in.LoginDTORequest;
import com.seleste.bff_agendador.business.DTO.in.TelefoneDTORequest;
import com.seleste.bff_agendador.business.DTO.in.UsuarioDTORequest;
import com.seleste.bff_agendador.business.DTO.out.EnderecoDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.TelefoneDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.UsuarioDTOResponse;
import com.seleste.bff_agendador.business.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários.")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salva Usuário", description = "Cria um novo usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso.")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return
                ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login do usuário.")
    @ApiResponse(responseCode = "200", description = "Login feito com sucesso.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public String login(@RequestBody LoginDTORequest loginDTO) {
        return usuarioService.loginUsuario(loginDTO);
    }

    @GetMapping
    @Operation(summary = "Busca Dados do Usuário por Email",
            description = "Busca os dados do Usuário através do Email.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                  @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário",
            description = "Deleta os dados do Usuário através do Email.")
    @ApiResponse(responseCode = "200", description = "Usuário deletado.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza Usuário",
            description = "Atualiza os dados do Usuário.")
    @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<UsuarioDTOResponse> atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                              @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço",
            description = "Atualiza os dados de endereço do Usuário.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                               @RequestParam("id") Long id,
                                                               @RequestHeader(value = "Authorization", required = false)
                                                        String token) {

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone",
            description = "Atualiza os dados de telefone do Usuário.")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                               @RequestParam("id") Long id,
                                                               @RequestHeader(value = "Authorization", required = false)
                                                        String token) {

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra Endereço",
            description = "Cadastra novo endereço de usuário.")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {

        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra Telefone",
            description = "Cadastra novo telefone de usuário.")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }
}
