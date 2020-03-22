package com.br.cadastro.listener;

import com.br.cadastro.model.Usuario;
import com.br.cadastro.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServerListener {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    UsuarioService usuarioService;


//    @RabbitListener(queues = "tut.rpc.requests")
    public String fibonacci(int n) throws JsonProcessingException {
        Optional<Usuario> usuario = usuarioService.findById(n);
        return usuario.isPresent() ?  mapper.writeValueAsString(usuario): null  ;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

    public int fatorial(int n) {
        int resultado = 1;
        for (int i = 1; i < n ; i ++) {
            if (i < Integer.MAX_VALUE) {
                resultado = resultado * i;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return resultado;
    }
}
