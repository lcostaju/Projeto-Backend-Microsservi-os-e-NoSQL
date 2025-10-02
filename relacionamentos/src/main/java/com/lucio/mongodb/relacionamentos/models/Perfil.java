package com.lucio.mongodb.relacionamentos.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collation = "perfis")
public class Perfil {
    @Id
    private String id;
    private String bio;
    private String avatarUrl;
}
