package com.lucio.api.product.product_api.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.lucio.api.product.product_api.models.Category;

public interface CategoryRepository extends MongoRepository<Category,String>{

}
