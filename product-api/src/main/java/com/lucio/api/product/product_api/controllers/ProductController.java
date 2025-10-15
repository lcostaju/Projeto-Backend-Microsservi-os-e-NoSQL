package com.lucio.api.product.product_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.lucio.api.product.product_api.models.dto.ProductDTO;
import com.lucio.api.product.product_api.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping("/identifier/{productIdentifier}")
    public ProductDTO findByProductIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> findByCategory(@PathVariable String categoryId) {
        return productService.findByCategory(categoryId);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getAllPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO edit(@PathVariable String id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.editProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }
}
