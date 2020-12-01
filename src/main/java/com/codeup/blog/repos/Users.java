package com.codeup.blog.repos;

import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
