package com.br.cadastro.controller;


import com.br.cadastro.model.JsonErrors;
import com.br.cadastro.model.JsonResponse;
import com.br.cadastro.model.Usuario;
import com.br.cadastro.reposytory.UsuarioRepository;
import com.br.cadastro.service.SequenceService;
import com.br.cadastro.service.UsuarioService;
import com.br.cadastro.util.Beans;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    SequenceService sequenceService;
    @Autowired
    UsuarioService usuarioService;

    private static final String VALOR_OBRIGATORIO = "Valor Obrigatorio";

    @GetMapping(value = "/usuario/{id}")
    public JsonResponse getById(@PathVariable("id") String id) {
        JsonErrors errors = Beans.validarLongValue(id);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        Optional<Usuario> usuario = usuarioRepository.findById(Long.parseLong(id));

        return usuario.isPresent() ? JsonResponse.ok(usuario.get()) : JsonResponse.error("Id informado não encontrado");
    }

    @PutMapping(value = "/usuario/{id}")
    public JsonResponse putUserPutById(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        JsonErrors errors = Beans.validarLongValue(id);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        long valor = Long.parseLong(id);
        if (valor > 0) {
            usuario.setId(valor);
        }

        errors = validateUsuario(usuario);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        Optional<Usuario> usuario1 = usuarioRepository.findById(valor);
        if (usuario1.isPresent()) {
            usuario.setCpf(usuario1.get().getCpf());
            usuarioRepository.save(usuario);
            return JsonResponse.ok(usuario);
        } else {
            return JsonResponse.error("Id informado não encontrado");
        }
    }

    @PatchMapping(value = "/usuario/{id}")
    public JsonResponse patchUserPatchById(@PathVariable("id") String id, @RequestBody Usuario usuario) {

        JsonErrors errors = Beans.validarLongValue(id);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        Usuario user = usuarioRepository.findByCpf(usuario.getCpf());
        if (user != null && user.getId() != Long.parseLong(id)) {
            return JsonResponse.error("CPF cadastrado em outro usuario");
        }

        Optional<Usuario> usuarioUpdate = usuarioRepository.findById(Long.parseLong(id));

        if (usuarioUpdate.isPresent()) {
            return JsonResponse.ok(usuarioService.updateUsuario(usuario, usuarioUpdate.get()));
        } else {
            return JsonResponse.error("Usuario não encontrado.");
        }

    }

    @PostMapping(value = "/usuario")
    public JsonResponse createUserPatchById(@RequestBody Usuario usuario) {
        JsonErrors errors = validateUsuario(usuario);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        usuario.setId(sequenceService.sequenceKey("usuario"));
        usuarioRepository.save(usuario);
        return JsonResponse.ok(usuario);
    }

    @DeleteMapping(value = "/usuario/{id}")
    public JsonResponse deleteUserPatchById(@PathVariable("id") String id) {

        JsonErrors errors = Beans.validarLongValue(id);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        Usuario usuario = usuarioService.findById(Long.parseLong(id));
        usuarioService.excluirUsuario(usuario);
        return JsonResponse.delete("usuario excluido com sucesso");
    }

    private JsonErrors validateUsuario(Usuario usuario) {
        JSONObject jsonObject = new JSONObject();
        if (Objects.isNull(usuario)) {
            if (Beans.isEmpty(usuario.getNomeComposto()))
                jsonObject.put("NomeComposto", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getCpf()))
                jsonObject.put("CPF", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getEndereco()))
                jsonObject.put("Endereço", VALOR_OBRIGATORIO);

            if (Beans.isEmpty(usuario.getEmail()))
                jsonObject.put("Email", VALOR_OBRIGATORIO);

            Usuario user = usuarioRepository.findByCpf(usuario.getCpf());
            if (usuario.getId() == 0 && user != null) {
                jsonObject.put("CPF", "Valor cadastrado em "+ user.getNomeComposto());
            }
        } else {
            jsonObject.put("Usuario", "Informe os valores conforme expecificação");
        }

        if (!jsonObject.isEmpty()) {
            return new JsonErrors(true, jsonObject);
        }

        return new JsonErrors(false, "");
    }


}
