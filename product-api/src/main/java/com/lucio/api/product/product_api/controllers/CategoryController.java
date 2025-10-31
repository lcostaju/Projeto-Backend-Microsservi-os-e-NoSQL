package com.lucio.api.product.product_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

// import com.lucio.api.product.product_api.models.dto.CategoryDTO;
import com.lucio.api.product.product_api.services.CategoryService;
import com.lucio.dto.CategoryDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public List<CategoryDTO> getAll() {
		return categoryService.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDTO create(@RequestBody @Valid CategoryDTO categoryDTO) {
		return categoryService.save(categoryDTO);
	}

	@PutMapping("/{id}")
	public CategoryDTO edit(@PathVariable String id, @RequestBody @Valid CategoryDTO categoryDTO) {
		return categoryService.editCategory(id, categoryDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		categoryService.delete(id);
	}

	@GetMapping("/pageable")
	public Page<CategoryDTO> getAllPage(Pageable pageable) {
		return categoryService.getAllPage(pageable);
	}
}
