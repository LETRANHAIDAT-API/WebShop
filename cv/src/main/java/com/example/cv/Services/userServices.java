package com.example.cv.Services;

import com.example.cv.Model.Role;
import com.example.cv.Model.users;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface userServices {
    users SaveUser(users user);
    Role saveRole(Role role);
    Void addToRole(String rolename, String username);
}
