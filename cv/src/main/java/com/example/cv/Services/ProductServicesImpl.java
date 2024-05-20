package com.example.cv.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cv.Model.Product;
import com.example.cv.Repository.productRepository;
@Service
public class ProductServicesImpl implements ProductServices{
    @Autowired
    private productRepository productRepository;
    @Override
    public List<Product> show() {
        return productRepository.findAll();
    }

    public void Add(Product p)
    {
        productRepository.save(p);
    }
    
}
