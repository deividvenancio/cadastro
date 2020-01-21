package com.br.cadastro.reposytory;

import com.br.cadastro.model.Products;
import com.br.cadastro.model.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsReposytory extends MongoRepository<Products, Long> {

    Products findByName(String name);
}
