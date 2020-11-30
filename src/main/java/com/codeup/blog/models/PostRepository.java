package com.codeup.blog.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface PostRepository extends JpaRepository<Post, Long> {
    ArrayList<Post> findAll();

    Post getById(long id);

    Post save(Post post);
}
