package com.lucio.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucio.mongodb.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
