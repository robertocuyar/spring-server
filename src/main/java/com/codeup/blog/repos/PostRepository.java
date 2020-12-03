package com.codeup.blog.repos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface PostRepository extends JpaRepository<Post, Long> {
    ArrayList<Post> findAll();

    Post getById(long id);

    Post save(Post post);

    Post deleteById(long id);

    Post getByTitle(String title);

}
