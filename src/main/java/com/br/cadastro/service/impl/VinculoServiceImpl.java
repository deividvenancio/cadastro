package com.br.cadastro.service.impl;

import com.br.cadastro.model.Vinculo;
import com.br.cadastro.reposytory.VinculoRepository;
import com.br.cadastro.service.VinculoService;
import com.br.cadastro.util.Beans;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinculoServiceImpl implements VinculoService {

    private VinculoRepository vinculoRepository;

    public VinculoServiceImpl(final  VinculoRepository vinculoRepository) {
        this.vinculoRepository = vinculoRepository;
    }

    @Override
    public void salvar(Vinculo vinculo) {
        vinculoRepository.save(vinculo);
    }

    public List<Vinculo> listByUsuario(long idUsuario) {
        return vinculoRepository.findAllByIdUsuario(idUsuario);
    }

    @Override
    public Optional<Vinculo> findByIdUsuarioAndroductName(long idUsuario, String productName) {
        return vinculoRepository.findByIdUsuarioAndProductNameAndStatus(idUsuario, productName, true);
    }

    @Override
    public List<Vinculo> listByProductName(String productName) {
        if (Beans.isEmpty(productName)) {
            return vinculoRepository.findAllByStatus(true);
        } else {
            return vinculoRepository.findAllByProductNameAndStatus(productName, true);
        }
    }
}
