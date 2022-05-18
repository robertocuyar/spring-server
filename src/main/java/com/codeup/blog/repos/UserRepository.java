package com.codeup.blog.repos;

import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getById(long id);

    User findByUsername(String username);

    User save(User User);
}
