package com.lucio.api.product.product_api.models.dto;

import com.lucio.api.product.product_api.models.Category;
import com.lucio.api.product.product_api.models.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;

    @NotBlank(message = "Pruduct identifier é obrigatorio")
    private String productIdentifier;

    @NotBlank(message = "nome é obrigatorio")
    private String nome;

    @NotBlank(message = "preco é obrigatorio")
    private Double preco;

    @NotBlank(message = "descricao é obrigatorio")
    private String descricao;

    @NotBlank(message = "category é obrigatorio")
    private Category category;

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setDescricao(product.getDescricao());
        product.setCategory(product.getCategory());

        return productDTO;

    }
}
