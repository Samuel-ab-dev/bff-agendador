package com.seleste.bff_agendador.business;

import com.seleste.bff_agendador.business.DTO.in.EnderecoDTORequest;
import com.seleste.bff_agendador.business.DTO.in.LoginDTORequest;
import com.seleste.bff_agendador.business.DTO.in.TelefoneDTORequest;
import com.seleste.bff_agendador.business.DTO.in.UsuarioDTORequest;
import com.seleste.bff_agendador.business.DTO.out.EnderecoDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.TelefoneDTOResponse;
import com.seleste.bff_agendador.business.DTO.out.UsuarioDTOResponse;
import com.seleste.bff_agendador.infrastructure.security.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.UsuarioDTOsalvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest loginDTO) {
        return client.login(loginDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return client.atualizaUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO,
                                                String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return client.EnderecoDTOcadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return client.cadastraTelefone(telefoneDTO, token);
    }
}
