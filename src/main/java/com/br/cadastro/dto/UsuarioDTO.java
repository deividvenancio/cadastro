package com.br.cadastro.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private long id;
    private String nomeComposto;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
}
