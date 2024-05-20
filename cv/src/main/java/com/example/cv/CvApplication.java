package com.example.cv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.cv")
public class CvApplication
{
    public static void main(String[] args)  {
		SpringApplication.run(CvApplication.class, args);
	}
}
