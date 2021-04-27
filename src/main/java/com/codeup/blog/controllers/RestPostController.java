package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.repos.PostRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class RestPostController {

    private final PostRepository postDao;

    RestPostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @CrossOrigin
    @GetMapping("/data")
    List<Post> all() {
        return postDao.findAll();
    }

}

