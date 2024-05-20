package com.example.cv.Dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The brand is required")
    private String brand;
    @NotEmpty(message = "The name is required")
    private String category;
    @Min(value = 0)
    private Double price;
    @Size(min = 10 ,message = "nhap tren 10")
    @Size(max = 200, message = "nhap duoi 200")
    private String description;
    private MultipartFile imageFile;
}
