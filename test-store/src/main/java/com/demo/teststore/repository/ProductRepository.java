package com.demo.teststore.repository;


import com.demo.teststore.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductRepository  extends MongoRepository<Product, BigInteger> {
    List<Product> findByCategory(String category);
}
