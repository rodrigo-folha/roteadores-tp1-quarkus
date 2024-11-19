package br.unitins.tp1.roteadores.service;

import br.unitins.tp1.roteadores.dto.UsuarioResponseDTO;

public interface JwtService {

    String generateJwt(UsuarioResponseDTO dto);
}
