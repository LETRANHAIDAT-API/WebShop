package com.example.cv.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Override
    public Boolean delete(Long Id,String urlfile) {
        return productRepository.findById(Id).map(imgs ->
        {
            String filename = imgs.getImageFileName();
            Path p = Paths.get(urlfile,filename );
            
                productRepository.deleteById(Id);
                try {
                    if(Files.deleteIfExists(p))
                    {
                        return true;
                    }else
                    {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return true;
        }).orElse(false);
    }

    @Override
    public List<Product> findProducts(String findpr) {
        return productRepository.findByName(findpr);
    }
}
