package com.br.cadastro.service;

import com.br.cadastro.model.Vinculo;

import java.util.List;
import java.util.Optional;

public interface VinculoService {

    void salvar(Vinculo vinculo);

    List<Vinculo> listByUsuario(long idUsuario);

    List<Vinculo> listByProductName(String productName);

    Optional<Vinculo> findByIdUsuarioAndroductName(long idUsuario, String productName);
}
