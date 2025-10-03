package com.lucio.mongodb.relacionamentos.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucio.mongodb.relacionamentos.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
