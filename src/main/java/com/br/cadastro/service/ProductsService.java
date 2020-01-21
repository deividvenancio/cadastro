package com.br.cadastro.service;

import com.br.cadastro.model.Products;

import java.util.List;


public interface ProductsService {

    void salvar(Products products);

    List<Products> listAll();

    Products findByName(String name);
}
