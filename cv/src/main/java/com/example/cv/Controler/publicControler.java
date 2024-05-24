package com.example.cv.Controler;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cv.Dto.loginDto;
import com.example.cv.Dto.signupDto;
import com.example.cv.Model.Role;
import com.example.cv.Model.users;
import com.example.cv.Repository.rolerRepository;
import com.example.cv.Repository.userRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class publicControler {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private rolerRepository rolerRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @GetMapping("dangnhap")
//    public String login() {
//        return "login";
//    }
    @GetMapping("/sucsessfull")
    public String redirectByRole(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/admin/Home";
            } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                return "redirect:/user/Home";
            }
        }
        // Nếu không xác định được vai trò, chuyển hướng đến một trang mặc định
        return "redirect:/public/defaultPage";
    }
    @GetMapping("/defaultPage")
    @ResponseBody
    public String defaultPage() {
        return "page Public";
    }

    @GetMapping("/signin")
    public String signin() {
        return "/login_logout/signin";
    }
    @PostMapping(value = "/si1")
    public String signin(@ModelAttribute @Valid loginDto loginDto, BindingResult result) {
        if(result.hasErrors())
        {
            return "/login_logout/signin";
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getNameOremail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "/login_logout/signin";
    }
    @GetMapping("/sinnup")
    public String signup()
    {
        return "/login_logout/signup";
    }
    @PostMapping(value = "/si2")
    public void signup(@Valid @ModelAttribute signupDto signupDto, BindingResult bindingResult) {
        // if (userRepository.existsByEmail(signupDto.getEmail())) {
        //     bindingResult.rejectValue("email", "email da ton tai");
        // } 
        // if (bindingResult.hasErrors()) {
        //     return "/login_logout/signup";
        // }
        users user = new users();
        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        Role role = rolerRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        //return "redirect:/public/signin";
    }

}

