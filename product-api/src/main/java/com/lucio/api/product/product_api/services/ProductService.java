package com.lucio.api.product.product_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucio.api.product.product_api.models.Product;
import com.lucio.api.product.product_api.models.Category;
import com.lucio.api.product.product_api.models.dto.ProductDTO;
import com.lucio.api.product.product_api.models.dto.CategoryDTO;
import com.lucio.api.product.product_api.repositories.ProductRepository;
import com.lucio.api.product.product_api.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDTO save(ProductDTO productDTO) {
        if (productDTO.getCategory() == null || productDTO.getCategory().getId() == null) {
            throw new RuntimeException("category.id é obrigatório");
        }
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductIdentifier(productDTO.getProductIdentifier());
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setCategory(category);
        Product saved = productRepository.save(product);
        return ProductDTO.convert(saved);
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }

    public ProductDTO findById(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductDTO.convert(product);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product == null) throw new RuntimeException("Product not found");
        return ProductDTO.convert(product);
    }

    public List<ProductDTO> findByCategory(String categoryId) {
        return productRepository.findByCategory_Id(categoryId).stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }

    public ProductDTO editProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        // Atualiza todos os campos do produto conforme o DTO recebido
        if (productDTO.getProductIdentifier() != null && !productDTO.getProductIdentifier().equals(product.getProductIdentifier())) {
            product.setProductIdentifier(productDTO.getProductIdentifier());
        }
        if (productDTO.getNome() != null && !productDTO.getNome().equals(product.getNome())) {
            product.setNome(productDTO.getNome());
        }
        if (productDTO.getPreco() != null && !productDTO.getPreco().equals(product.getPreco())) {
            product.setPreco(productDTO.getPreco());
        }
        if (productDTO.getDescricao() != null && !productDTO.getDescricao().equals(product.getDescricao())) {
            product.setDescricao(productDTO.getDescricao());
        }
        if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        product = productRepository.save(product);
        return ProductDTO.convert(product);
    }

    public void delete(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public Page<ProductDTO> getAllPage(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::convert);
    }
}
