package com.example.cv.Services;

import com.example.cv.Model.Role;
import com.example.cv.Model.users;
import com.example.cv.Repository.rolerRepository;
import com.example.cv.Repository.userRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements userServices{

    private  userRepository userRepository;
    private  rolerRepository rolerRepository;
    private  PasswordEncoder passwordEncoder;
    @Override
    public users SaveUser(users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return rolerRepository.save(role);
    }

    @Override
    public Void addToRole(String rolename, String username) {
        users user = userRepository.findByEmail(username).get();
        Role role = rolerRepository.findByName(rolename);
        user.getRoles().add(role);
        return null;
    }
}
