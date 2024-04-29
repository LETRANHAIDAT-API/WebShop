package com.example.cv;

import com.example.cv.Model.Role;
import com.example.cv.Model.users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.cv.Repository.userRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = "com.example.cv")
public class CvApplication{

    public static void main(String[] args) {
		SpringApplication.run(CvApplication.class, args);
	}
}
