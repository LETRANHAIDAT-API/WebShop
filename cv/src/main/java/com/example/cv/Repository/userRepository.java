package com.example.cv.Repository;

import com.example.cv.Model.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<users, Long> {
    users findByName(String name);

    Optional<users> findByEmail(String username);

    boolean existsByEmail(String email);
}
