package com.example.cv.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cv.Model.Product;
import com.example.cv.Services.ProductServicesImpl;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class userControler {
    @Autowired
    private ProductServicesImpl productServicesImpl;
    @GetMapping("/Home")
    public String Home(Model model) {
        List<Product> l = productServicesImpl.show();
        model.addAttribute("find", l);
        return "/Home/User/view";
    }
    @PostMapping("/find")
    public String find(@RequestParam(name = "username", required=false) String find,Model model)
    {
        System.out.println(find);
        List<Product> l =productServicesImpl.findProducts(find); 
        model.addAttribute("find", l);
        return "/Home/User/view";
    }
    @PostMapping("/loc")
    public String locsp(@RequestParam(name = "options",required = false) List<String> opList,Model model)
    {
        opList.stream().forEach(s ->System.out.println(s));
        if(opList.isEmpty())
        {
            
        }else
        {
            
        }
        return "";
    }
}
