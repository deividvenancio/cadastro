package com.br.cadastro.reposytory;

import com.br.cadastro.model.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, Long> {

    Sequence findByChave(String chave);
}
