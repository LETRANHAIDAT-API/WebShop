package com.example.cv.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cv.Model.Product;

@Service
public interface ProductServices {
       List<Product> show();
       void delete(Long Id);
}
