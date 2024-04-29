package com.example.cv.Controler;

import com.example.cv.Dto.loginDto;
import com.example.cv.Dto.signupDto;
import com.example.cv.Model.Role;
import com.example.cv.Model.users;
import com.example.cv.Repository.rolerRepository;
import com.example.cv.Repository.userRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
@Slf4j
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
    public ResponseEntity<String> signin(@ModelAttribute loginDto loginDto) {
        System.out.println(loginDto.getNameOremail());
        System.out.println(loginDto.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getNameOremail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("signin__Succsessfull", HttpStatus.OK);
    }
    @GetMapping("/sinnup")
    public String signup()
    {
        return "/login_logout/signup";
    }
    @PostMapping(value = "/si2")
    public ResponseEntity<String> signup(@ModelAttribute signupDto signupDto) {
        if(userRepository.existsByEmail(signupDto.getEmail()))
        {
            return new ResponseEntity<>("Email Already Exist", HttpStatus.CONFLICT);
        }
        users user = new users();
        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        Role role = rolerRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseEntity<>("signup__Succsessfull", HttpStatus.OK);
    }
}

