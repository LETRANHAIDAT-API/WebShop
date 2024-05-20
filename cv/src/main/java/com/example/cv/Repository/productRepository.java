package com.example.cv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cv.Model.Product;
@Repository
public interface productRepository extends JpaRepository<Product,Long>{
    
}
