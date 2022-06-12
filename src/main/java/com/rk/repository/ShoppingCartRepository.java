package com.rk.repository;

import com.rk.model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {
    public ShoppingCart findByUsername(String username);

}
