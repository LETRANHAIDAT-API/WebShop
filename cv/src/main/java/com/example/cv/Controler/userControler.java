package com.example.cv.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cv.Repository.userRepository;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class userControler {
    @Autowired
    private userRepository userRepository;
    @GetMapping("/Home")
    public String Home() {
        return "/Home/User/view";
    }
}
