package com.br.cadastro.controller;

import com.br.cadastro.model.JsonErrors;
import com.br.cadastro.model.JsonResponse;
import com.br.cadastro.model.Products;
import com.br.cadastro.model.Usuario;
import com.br.cadastro.service.ProductsService;
import com.br.cadastro.service.SequenceService;
import com.br.cadastro.util.Beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductsController {

    @Autowired
    ProductsService productsService;
    @Autowired
    SequenceService sequenceService;


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public JsonResponse saveProduct(@RequestBody Products products) {

        products.setId(sequenceService.sequenceKey("products"));
        productsService.salvar(products);
        return JsonResponse.ok(products);
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public JsonResponse LISTProduct() {

        return JsonResponse.ok(productsService.listAll());
    }

}
