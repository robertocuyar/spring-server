package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String postIndex (Model model){
        ArrayList<Post> postList = new ArrayList<>();
        postList.add(new Post(
                "My favorite snack",
                "We all need to eat popcorn. The main reason why is because popcorn is a quick to feel full even though we don't eat a whole lot of food overall. Popcorn should rain from the skies."
        ));
        postList.add(new Post(
                "What to do",
                "Well, we really don't know what to do at times. Maybe we should just code and think it is okay. Or maybe...just maybe...we can just see if this works."
        ));
        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable long id, Model model){
        Post post = new Post(
                "My favorite snack",
                "We all need to eat popcorn. The main reason why is because popcorn is a quick to feel full even though we don't eat a whole lot of food overall. Popcorn should rain from the skies."
        );
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postCreateForm (){
        return "view the form for creating the post.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate () {
        return "create a new post";
    }

}
