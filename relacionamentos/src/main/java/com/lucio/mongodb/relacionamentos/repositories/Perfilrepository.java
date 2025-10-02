package com.lucio.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucio.mongodb.relacionamentos.models.Perfil;

public interface Perfilrepository extends MongoRepository<Perfil, String>{

}
