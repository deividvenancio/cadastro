package com.br.cadastro.controller;

import com.br.cadastro.dto.UsuarioDTO;
import com.br.cadastro.exceptions.ValidationException;
import com.br.cadastro.model.JsonErrors;
import com.br.cadastro.model.JsonResponse;
import com.br.cadastro.model.Usuario;
import com.br.cadastro.service.SequenceService;
import com.br.cadastro.service.UsuarioService;
import com.br.cadastro.util.Beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {


    @Autowired UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public JsonResponse getById(@PathVariable("id") String id) {
        Beans.validarLongValue(id);

        Optional<Usuario> usuario = usuarioService.findById(Long.parseLong(id));

        if (usuario.isPresent()) {
            return JsonResponse.ok(usuario.get());
        }

        throw new ValidationException("Usuario com id: {0} não encontrado.", id);
    }

    @GetMapping(value = "/all")
    public JsonResponse getAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return JsonResponse.ok(usuarios);
    }

    @PutMapping(value = "/{id}")
    public JsonResponse putUserById(@PathVariable("id") String id, @RequestBody UsuarioDTO usuario) {
        Beans.validarLongValue(id);

        long valor = Long.parseLong(id);
        if (valor > 0) {
            usuario.setId(valor);
        }

//        errors = validateUsuario(usuario);
//        if (errors.isHasError()) {
//            return JsonResponse.error(errors);
//        }

        Optional<Usuario> usuario1 = usuarioService.findById(valor);
        if (usuario1.isPresent()) {
            usuario.setCpf(usuario1.get().getCpf());
            usuarioService.save(usuario);
            return JsonResponse.ok(usuario);
        }

        throw new ValidationException("Usuario com id: {0} não encontrado.", id);
    }

    @PatchMapping(value = "/{id}")
    public JsonResponse patchUserById(@PathVariable("id") String id, @RequestBody Usuario usuario) {

        Beans.validarLongValue(id);

        Usuario user = usuarioService.findByCpf(usuario.getCpf());
        if (user != null && user.getId() != Long.parseLong(id)) {
            throw new ValidationException("CPF cadastrado em outro usuario");
        }

        Optional<Usuario> usuarioUpdate = usuarioService.findById(Long.parseLong(id));

        if (usuarioUpdate.isPresent()) {
            return JsonResponse.ok(usuarioService.updateUsuario(usuario, usuarioUpdate.get()));
        }

        throw new ValidationException("Usuario não encontrado.");
    }

    @PostMapping(value = "")
    public JsonResponse postUser(@RequestBody UsuarioDTO usuario) {
        usuarioService.validarNovoUsuario(usuario);

        usuarioService.save(usuario);
        return JsonResponse.ok(usuario);
    }

    @DeleteMapping(value = "/usuario/{id}")
    public JsonResponse deleteUserById(@PathVariable("id") String id) {
        Beans.validarLongValue(id);

        Optional<Usuario> usuario = usuarioService.findById(Long.parseLong(id));
        usuario.ifPresent(usuario1 -> usuarioService.excluirUsuario(usuario1));

        return JsonResponse.ok("usuario excluido com sucesso");
    }


}
