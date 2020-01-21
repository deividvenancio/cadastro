package com.br.cadastro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Products {

    @Id
    private long id;
    private String name;
}
