package com.br.cadastro.service.impl;

import com.br.cadastro.dto.UsuarioDTO;
import com.br.cadastro.exceptions.ValidationException;
import com.br.cadastro.model.JsonErrors;
import com.br.cadastro.model.Usuario;
import com.br.cadastro.reposytory.UsuarioRepository;
import com.br.cadastro.service.SequenceService;
import com.br.cadastro.service.UsuarioService;
import com.br.cadastro.util.Beans;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.br.cadastro.util.Beans.isNotEmpty;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final String VALOR_OBRIGATORIO = "Valor Obrigatorio";

    private UsuarioRepository usuarioRepository;
    private SequenceService sequenceService;
    private ModelMapper modelMapper;

    @Autowired
    public UsuarioServiceImpl(final UsuarioRepository usuarioRepository,
                              final SequenceService sequenceService,
                              final ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.sequenceService = sequenceService;
        this.modelMapper = modelMapper;
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
    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getId() == 0) {
            usuarioDTO.setId(sequenceService.sequenceKey("usuario"));
        }
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public void validarNovoUsuario(UsuarioDTO usuario) {
        JSONObject jsonObject = new JSONObject();
        if (!Objects.isNull(usuario)) {
            if (Beans.isEmpty(usuario.getNomeComposto()))
                jsonObject.put("NomeComposto", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getCpf()))
                jsonObject.put("CPF", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getEndereco()))
                jsonObject.put("Endereço", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getEmail()))
                jsonObject.put("Email", VALOR_OBRIGATORIO);

            Usuario user = findByCpf(usuario.getCpf());
            if (usuario.getId() == 0 && user != null) {
                jsonObject.put("CPF", "Valor cadastrado em "+ user.getNomeComposto());
            }
        } else {
            jsonObject.put("Usuario", "Informe os valores conforme expecificação");
        }

        if (!jsonObject.isEmpty()) {
            throw new ValidationException(jsonObject.toString());
        }
    }
}
