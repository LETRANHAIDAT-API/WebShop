package com.example.cv.Services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cv.Model.users;
import com.example.cv.Repository.userRepository;

@Service
public class customUserDetailsServices implements UserDetailsService {
    private final userRepository userRepository;

    public customUserDetailsServices(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users users = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        Set<GrantedAuthority> authorities = users.getRoles().stream().map((i)-> new SimpleGrantedAuthority(i.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), authorities);
    }
}
