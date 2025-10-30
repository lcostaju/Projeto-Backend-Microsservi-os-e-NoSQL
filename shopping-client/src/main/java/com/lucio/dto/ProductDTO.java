package com.lucio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;

    // productIdentifier não é mais obrigatório para permitir PUT sem esse campo
    private String productIdentifier;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotNull(message = "preco é obrigatório")
    private Double preco;

    @NotBlank(message = "descricao é obrigatória")
    private String descricao;

    @NotNull(message = "category é obrigatório")
    private CategoryDTO category;

    // public static ProductDTO convert(Product product){
    //     ProductDTO productDTO = new ProductDTO();
    //     productDTO.setId(product.getId());
    //     productDTO.setProductIdentifier(product.getProductIdentifier());
    //     productDTO.setNome(product.getNome());
    //     productDTO.setPreco(product.getPreco());
    //     productDTO.setDescricao(product.getDescricao());
    //     if (product.getCategory() != null) {
    //         productDTO.setCategory(CategoryDTO.convert(product.getCategory()));
    //     }
    //     return productDTO;
    // }
}
