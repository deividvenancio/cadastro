package com.br.cadastro.service.impl;

import com.br.cadastro.model.Products;
import com.br.cadastro.reposytory.ProductsReposytory;
import com.br.cadastro.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductsReposytory productsReposytory;

    public ProductsServiceImpl(final ProductsReposytory productsReposytory) {
        this.productsReposytory = productsReposytory;
    }

    @Override
    public void salvar(Products products) {
        productsReposytory.save(products);
    }

    @Override
    public List<Products> listAll() {
        return productsReposytory.findAll();
    }

    @Override
    public Products findByName(String name) {
        return productsReposytory.findByName(name);
    }
}
