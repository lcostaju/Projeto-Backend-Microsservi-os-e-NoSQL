package com.lucio.mongodb.relacionamentos.relacionamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucio.mongodb.relacionamentos.relacionamentos.models.Perfil;
import com.lucio.mongodb.relacionamentos.relacionamentos.models.Usuario;
import com.lucio.mongodb.relacionamentos.relacionamentos.repositories.Perfilrepository;
import com.lucio.mongodb.relacionamentos.relacionamentos.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Perfilrepository perfilrepository;

    // GET /usuarios - retorna todos os usuários, com seus relacionamentos
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // GET /usuarios/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public Usuario getById(@PathVariable String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // POST /usuarios - adiciona um usuário
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        if (usuario.getPerfil() != null && usuario.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilrepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
        }

        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario editUsuario(@PathVariable String id, @RequestBody Usuario usuarioDto) {
        Usuario usuarioExistente = usuarioRepository
                .findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Atualize os campos necessários do usuarioExistente com os valores do usuarioDto
        usuarioExistente.setNome(usuarioDto.getNome());
        usuarioExistente.setPerfil(usuarioDto.getPerfil());
        // Adicione outros campos conforme necessário

        return usuarioRepository.save(usuarioExistente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        Usuario user = usuarioRepository
        .findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        usuarioRepository.delete(user);
    }
}
