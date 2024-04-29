package com.example.cv.Controler;

import com.example.cv.Model.users;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cv.Repository.userRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

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
