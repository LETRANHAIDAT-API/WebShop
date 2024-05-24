package com.example.cv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cv.Model.Product;
@Repository
public interface productRepository extends JpaRepository<Product,Long>{

    @Query(nativeQuery = true , value = "select * from product where category ilike ?1% ")
    List<Product> findByName(String findpr);
}
