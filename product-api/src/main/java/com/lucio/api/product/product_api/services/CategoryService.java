package com.lucio.api.product.product_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucio.api.product.product_api.converter.DTOConverter;
import com.lucio.api.product.product_api.models.Category;
// import com.lucio.api.product.product_api.models.dto.CategoryDTO;
import com.lucio.api.product.product_api.repositories.CategoryRepository;
import com.lucio.dto.CategoryDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public CategoryDTO save(CategoryDTO categoryDTO){
        Category category = Category.fromDTO(categoryDTO);
        Category saved = categoryRepository.save(category);
        return DTOConverter.convert(saved);
    }

    public CategoryDTO delete(String categoryId) {
        Category category = categoryRepository
            .findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
        return DTOConverter.convert(category);
    }

    public CategoryDTO editCategory(String categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository
            .findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

        // Atualiza apenas se o nome for diferente e não nulo
        if (categoryDTO.getNome() != null && !categoryDTO.getNome().equals(category.getNome())) {
            category.setNome(categoryDTO.getNome());
        }

        category = categoryRepository.save(category);
        return DTOConverter.convert(category);
    }

    public Page<CategoryDTO> getAllPage(Pageable page){
    Page<Category> categories = categoryRepository.findAll(page);
    return categories
    .map(DTOConverter::convert);
}



}
