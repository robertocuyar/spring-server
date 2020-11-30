package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.PostRepository;
import com.codeup.blog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {

    private final PostRepository postDAO;
    private final UserRepository userDAO;

    public PostController (PostRepository postDAO, UserRepository userDAO){
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }
    @GetMapping("/posts")
    public String postIndex (Model model){
        model.addAttribute("posts", postDAO.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable long id, Model model){
        model.addAttribute("post", postDAO.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String postCreateForm (){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public RedirectView postCreate (@RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        Post createPost = new Post (title, body, userDAO.getById(2));
        postDAO.save(createPost);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/delete/{id}")
    public RedirectView postDelete (@PathVariable long id){
        postDAO.deleteById(id);
        return new RedirectView("/posts");
    }

    @GetMapping ("/posts/edit/{id}")
    public String postEdit (@PathVariable long id, Model model){
        model.addAttribute("post", postDAO.getById(id));
        return "posts/edit";
    }

    @PostMapping ("/posts/edit/{id}")
    public RedirectView postEditResult (@PathVariable long id, @RequestParam(name="title") String title, @RequestParam(name="body") String body){
        Post editPost = postDAO.getById(id);
        editPost.setTitle(title);
        editPost.setBody(body);
        postDAO.save(editPost);
        return new RedirectView("/posts");
    }




}
