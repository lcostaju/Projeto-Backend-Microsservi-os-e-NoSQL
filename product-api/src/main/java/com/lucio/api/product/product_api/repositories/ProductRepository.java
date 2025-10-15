package com.lucio.api.product.product_api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lucio.api.product.product_api.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory_Id(String categoryId);
    Product findByProductIdentifier(String productIdentifier);
}
