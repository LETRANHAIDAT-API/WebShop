package com.example.cv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cv.Model.Product;
@Repository
public interface productRepository extends JpaRepository<Product,Long>{

    @Query(nativeQuery = true , value = "SELECT * FROM product WHERE name ILIKE :name%")
    List<Product> findByName(@Param("name")String findpr);
}
