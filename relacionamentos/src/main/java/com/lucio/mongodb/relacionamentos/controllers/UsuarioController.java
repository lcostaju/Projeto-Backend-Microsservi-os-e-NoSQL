package com.lucio.mongodb.relacionamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucio.mongodb.relacionamentos.models.Perfil;
import com.lucio.mongodb.relacionamentos.models.Usuario;
import com.lucio.mongodb.relacionamentos.repositories.Perfilrepository;
import com.lucio.mongodb.relacionamentos.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Perfilrepository perfilrepository;

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario create (@RequestBody Usuario usuario){
        if(usuario.getPerfil() != null && usuario.getPerfil().getId() == null){
            Perfil perfilSalvo = perfilrepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }

        return usuarioRepository.save(usuario);
    }

    
}
