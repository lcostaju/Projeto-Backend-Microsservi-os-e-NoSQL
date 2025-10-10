package com.lucio.api.product.product_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @Field("product_identifier")
    private String productIdentifier;

    @Field("nome")
    private String nome;

    @Field("preco")
    private Double preco;

    @Field("descricao")
    private String descricao;

    @Field("category")
    private Category category;
}
