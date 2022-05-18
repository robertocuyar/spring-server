package com.codeup.blog.controllers;

import com.codeup.blog.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class RestUserController {
    private final UserRepository userDao;

    RestUserController(UserRepository userDao) {this.userDao = userDao;}

    @CrossOrigin
    @PostMapping("/create")
    public String CreateUser(@RequestBody UserDetails) {

    }
}