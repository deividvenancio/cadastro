package com.br.cadastro.controller;

import com.br.cadastro.model.*;
import com.br.cadastro.service.ProductsService;
import com.br.cadastro.service.SequenceService;
import com.br.cadastro.service.UsuarioService;
import com.br.cadastro.service.VinculoService;
import com.br.cadastro.util.Beans;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class VinculoController {

    @Autowired SequenceService sequenceService;
    @Autowired VinculoService vinculoService;
    @Autowired ProductsService productsService;
    @Autowired UsuarioService usuarioService;

    @RequestMapping(value = "/vinculo", method = RequestMethod.POST)
    public JsonResponse saveVinculo(@RequestBody Vinculo vinculo) {

        JsonErrors errors = validarUsuarioAndProduct(vinculo.getIdUsuario(), vinculo.getProductName());

        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        checkVinculoAnterior(vinculo.getIdUsuario(), vinculo.getProductName());
        vinculo.setId(sequenceService.sequenceKey("vinculo"));
        vinculo.setDataVinculo(new Date());
        vinculo.setStatus(true);
        vinculoService.salvar(vinculo);

        return JsonResponse.ok(vinculo);
    }

    @RequestMapping(value = "/vinculo/usuario/{idUsuario}", method = RequestMethod.GET)
    public JsonResponse listVinculosByUsuario(@PathVariable("idUsuario") String id) {
        JsonErrors errors = Beans.validarLongValue(id);
        if (errors.isHasError()) {
            return JsonResponse.error(errors);
        }

        return JsonResponse.ok(vinculoService.listByUsuario(Long.parseLong(id)));
    }

    @RequestMapping(value = {"/vinculo/products/{productName}"},  method = RequestMethod.GET)
    public JsonResponse listVinculosByProduct(@PathVariable("productName") String productName) {
        return JsonResponse.ok(vinculoService.listByProductName(productName));
    }

    @RequestMapping(value = {"/vinculo/products/"},  method = RequestMethod.GET)
    public JsonResponse listVinculosByProduct() {
        return JsonResponse.ok(vinculoService.listByProductName(null));
    }

    private void checkVinculoAnterior(long usuarioId, String productName) {
        Optional<Vinculo> vinculo = vinculoService.findByIdUsuarioAndroductName(usuarioId, productName);
        if (vinculo.isPresent()) {
            vinculo.get().setStatus(false);
            vinculoService.salvar(vinculo.get());
        }
    }

    private JsonErrors validarUsuarioAndProduct(long usuarioId, String productName) {
        JSONObject jsonObject = new JSONObject();

        Products products = productsService.findByName(productName);
        Usuario usuario = usuarioService.findById(usuarioId);

        if (products == null) {
            jsonObject.put("productId", "Produto não encontrado");
        }

        if (usuario == null) {
            jsonObject.put("usuarioId", "Usuario não encontrado");
        }

        if (!jsonObject.isEmpty()) {
            return new JsonErrors(true, jsonObject);
        }

        return new JsonErrors(false, "");
    }
}
