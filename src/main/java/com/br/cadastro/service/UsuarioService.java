package com.br.cadastro.service;

import com.br.cadastro.model.Usuario;

public interface UsuarioService {

    Usuario updateUsuario(Usuario usuario, Usuario usuarioUpdate);

    Usuario findById(long id);

    void excluirUsuario(Usuario usuario);
}
