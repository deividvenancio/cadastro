package com.br.cadastro.service.impl;

import com.br.cadastro.model.Usuario;
import com.br.cadastro.reposytory.UsuarioRepository;
import com.br.cadastro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.br.cadastro.util.Beans.isNotEmpty;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(final UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, Usuario usuarioUpdate) {

        if (isNotEmpty(usuario.getNomeComposto())) {
            usuarioUpdate.setNomeComposto(usuario.getNomeComposto());
        }

        if (isNotEmpty(usuario.getCpf())) {
            usuarioUpdate.setCpf(usuario.getCpf());
        }

        if (isNotEmpty(usuario.getEmail())) {
            usuarioUpdate.setEmail(usuario.getEmail());
        }

        if (isNotEmpty(usuario.getEndereco())) {
            usuarioUpdate.setEndereco(usuario.getEndereco());
        }

        if (isNotEmpty(usuario.getTelefone())) {
            usuarioUpdate.setTelefone(usuario.getTelefone());
        }

        usuarioRepository.save(usuarioUpdate);
        return usuarioUpdate;
    }

    @Override
    public Usuario findById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
