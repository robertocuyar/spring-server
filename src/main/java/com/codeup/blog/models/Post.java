package com.codeup.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false, length = 100)
    private String title;

    @Column (nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn (nullable = false, name = "user_id")
    private User user;


    public Post (){};

    public Post (String title, String body, User user){
        this.title = title;
        this.body = body;
        this.user = user;
    }
    public Post (String title, String body, long id, User user){
        this.title = title;
        this.body = body;
        this.id = id;
        this.user = user;
    }

    public String getTitle(){ return title; }
    public void setTitle (String title){ this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body){ this.body = body; }
    public long getId () { return id; }
    public void setId (long id) { this.id = id; }
    @JsonIgnore
    public User getUser(){ return user; }
    public void setUser(User user) { this.user = user; }
}
