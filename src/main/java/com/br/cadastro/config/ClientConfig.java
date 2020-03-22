package com.br.cadastro.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("tut.rpc");
    }

}
