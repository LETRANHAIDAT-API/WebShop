package com.example.cv.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cv.Model.Product;
import com.example.cv.Services.ProductServicesImpl;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class userControler {
    @Autowired
    private ProductServicesImpl productServicesImpl;
    @Autowired
    @GetMapping("/Home")
    public String Home() {
        return "/Home/User/view";
    }
    @PostMapping("find")
    public String find(@ModelAttribute String find,Model model)
    {
        List<Product> l =productServicesImpl.findProducts(find); 
        model.addAttribute("find", l);
        return "/Home/User/view";
    }
}
