package com.lucio.mongodb.relacionamentos.relacionamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucio.mongodb.relacionamentos.relacionamentos.models.Perfil;
import com.lucio.mongodb.relacionamentos.relacionamentos.models.Usuario;
import com.lucio.mongodb.relacionamentos.relacionamentos.repositories.Perfilrepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private Perfilrepository perfilrepository;

    //GET /perfis - retorna todos
    @GetMapping
    public List<Perfil> getAll() {
        return perfilrepository.findAll();
    }
    


    //POST /perfis - adiciona um
    @PostMapping
    public Perfil create(@RequestBody Perfil perfil){
        return perfilrepository.save(perfil);
    }

    //GET /perfis/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public Perfil findById(@PathVariable String id){
         return perfilrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //PUT /perfis/{id} - atualiza um
    @PutMapping("/{id}")
    public Perfil edit(@PathVariable String id,@RequestBody Perfil perfil){
        Perfil perfilExistente = perfilrepository
                .findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        perfilExistente.setAvatarUrl(perfil.getAvatarUrl());
        perfilExistente.setBio(perfil.getBio());

        return perfilrepository.save(perfilExistente);
    }

    //DELETE /perfis/{id} - deleta um
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
         Perfil perfil = perfilrepository
        .findById(id).orElseThrow(() -> new RuntimeException("perfil not found"));
        perfilrepository.delete(perfil);
    }

}
