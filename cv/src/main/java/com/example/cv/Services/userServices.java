package com.example.cv.Services;

import org.springframework.stereotype.Service;

import com.example.cv.Model.Role;
import com.example.cv.Model.users;

@Service
public interface userServices {
    users SaveUser(users user);
    Role saveRole(Role role);
    Void addToRole(String rolename, String username);
}
