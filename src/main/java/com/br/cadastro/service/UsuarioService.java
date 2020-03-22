package com.br.cadastro.service;

import com.br.cadastro.dto.UsuarioDTO;
import com.br.cadastro.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario updateUsuario(Usuario usuario, Usuario usuarioUpdate);

    Optional<Usuario> findById(long id);

    List<Usuario> findAll();

    Usuario findByCpf(String cpf);

    void save(UsuarioDTO usuario);

    void excluirUsuario(Usuario usuario);

    void validarNovoUsuario(UsuarioDTO usuarioDTO);
}
