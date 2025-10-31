package com.lucio.api.product.product_api.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.lucio.dto.CategoryDTO;

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

    // public static Category convert(CategoryDTO categoryDTO){
    //     Category category = new Category();
    //     category.setId(categoryDTO.getId());
    //     category.setNome(categoryDTO.getNome());

    //     return category;
    // }

    public static Category fromDTO(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setNome(categoryDTO.getNome());
        return category;
    }
}
