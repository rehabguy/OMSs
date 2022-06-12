package com.rk.repository;

import com.rk.model.Category;
import com.rk.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findByName(String productName);

    List<Product> findByCategory(Category category);

    Optional<Product> findBySku(String sku);
}
