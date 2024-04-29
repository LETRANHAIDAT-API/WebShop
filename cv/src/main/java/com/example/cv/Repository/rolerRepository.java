package com.example.cv.Repository;

import com.example.cv.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolerRepository extends JpaRepository<Role,Long> {
    Role findByName(String rolename);
}
