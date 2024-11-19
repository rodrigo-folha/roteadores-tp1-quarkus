package br.unitins.tp1.roteadores.service;

import java.util.List;

import br.unitins.tp1.roteadores.model.Usuario;

public interface UsuarioService {
    
    Usuario findById(Long id);

    Usuario findByEmailAndSenha(String email, String senha);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    void delete(Long id); 
}
