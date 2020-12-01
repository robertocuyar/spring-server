package com.codeup.blog.controllers;

import com.codeup.blog.SecurityConfiguration;
import com.codeup.blog.models.EmailService;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repos.PostRepository;
import com.codeup.blog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {

    private final PostRepository postDAO;
    private final UserRepository userDAO;
    private final EmailService emailService;

    public PostController (PostRepository postDAO, UserRepository userDAO, EmailService emailService){
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.emailService = emailService;
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
    public String postCreateForm (Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public RedirectView postCreate (@ModelAttribute Post post) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postDAO.save(post);
        emailService.prepareAndSend(post,"Creation", "You created a new blog post!");
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
    public RedirectView postEditResult (@PathVariable long id, @ModelAttribute Post post){
       Post editPost = postDAO.getById(id);
        editPost.setTitle(post.getTitle());
        editPost.setBody(post.getBody());
        postDAO.save(editPost);
        return new RedirectView("/posts");
    }




}
