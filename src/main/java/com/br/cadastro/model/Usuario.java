package com.br.cadastro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class Usuario {

    @Id
    private long id;
    private String nomeComposto;
    @Indexed(unique = true)
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

}
