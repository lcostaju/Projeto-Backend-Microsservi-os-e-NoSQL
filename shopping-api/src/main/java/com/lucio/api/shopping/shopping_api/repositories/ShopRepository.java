package com.lucio.api.shopping.shopping_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lucio.api.shopping.shopping_api.models.Shop;
import java.util.List;

public interface ShopRepository extends MongoRepository<Shop, String> {
    List<Shop> findByUserIdentifier(String userIdentifier);
    List<Shop> findByDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
    List<Shop> findByTotalGreaterThan(Double minTotal);
}
