package com.lucio.api.product.product_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {
    @Id
    private String id;
    @Field("nome")
    private String nome;
}
