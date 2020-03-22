package com.br.cadastro.job;

import com.br.cadastro.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientJob {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private ObjectMapper mapper;

    int start = 0;

//    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send() throws JsonProcessingException {
        System.out.println(" [x] Requesting fib(" + start + ")");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        template.setReceiveTimeout(10000);
        String usuario = (String) template.convertSendAndReceive
                (exchange.getName(), "rpc", start++, correlationData);

        if (usuario != null) {
            Usuario usuario1 = mapper.readValue(usuario, Usuario.class);
            System.out.println(" [.] Got '" + usuario + "'");
        }
    }
}
