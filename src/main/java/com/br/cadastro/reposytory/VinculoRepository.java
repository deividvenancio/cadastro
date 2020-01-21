package com.br.cadastro.reposytory;

import com.br.cadastro.model.Vinculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VinculoRepository extends MongoRepository<Vinculo, Long> {

    List<Vinculo> findAllByIdUsuario(long idUsuario);

    List<Vinculo> findAllByProductNameAndStatus(String productName, boolean status);

    List<Vinculo> findAllByStatus(boolean status);

    Optional<Vinculo> findByIdUsuarioAndProductNameAndStatus(long idUsuario, String productName, boolean status);
}
