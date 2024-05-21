package com.example.cv.Controler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.cv.Dto.ProductDto;
import com.example.cv.Model.Product;
import com.example.cv.Services.ProductServicesImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class adminControler {
    @Autowired
    private ProductServicesImpl productServicesImpl;
    @GetMapping("/Home")
    public String Home(Model model) {
        List<Product> L = productServicesImpl.show();
        model.addAttribute("product",L );
        return "/Home/Admin/view";
    }
    @GetMapping("/create")
    public String createForm(Model model)
    {
        model.addAttribute("productDto", new ProductDto());
        return "/Home/Admin/create";
    }
    @PostMapping("/products/add")
    public String add(@ModelAttribute @Valid ProductDto productDto) throws IOException
    {
        Product p = new Product();
        p.setName(productDto.getName());
        p.setPrice(productDto.getPrice());
        p.setCategory(productDto.getCategory());
        p.setCreatedAt(LocalDate.now());
        p.setDescrption(productDto.getDescription());
        MultipartFile imageFile = productDto.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            String originalFileName = imageFile.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String UPLOAD_DIR = "cv/src/main/resources/static/img/";
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, imageFile.getBytes());
            p.setImageFileName(fileName);
        }
        productServicesImpl.Add(p);
        return "redirect:/admin/Home";
    }
}
