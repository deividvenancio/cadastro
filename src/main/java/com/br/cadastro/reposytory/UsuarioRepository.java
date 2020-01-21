package com.br.cadastro.reposytory;

import com.br.cadastro.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

    Usuario findByCpf(String cpf);
}
