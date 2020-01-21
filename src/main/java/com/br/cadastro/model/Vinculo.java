package com.br.cadastro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "vinculo")
public class Vinculo {

    @Id
    private long id;
    private boolean status;
    private long idUsuario;
    private String productName;
    private Date dataVinculo;
}
