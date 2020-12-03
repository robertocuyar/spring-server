package com.codeup.blog;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repos.PostRepository;
import com.codeup.blog.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BlogApplication.class)
@AutoConfigureMockMvc
class BlogApplicationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() throws Exception {
        testUser = userDao.findByUsername("testUser");

        if (testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    void contextLoads() {
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception {
        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "test")
                .param("body", "for sale"))
                .andExpect(redirectedUrl("/posts"));

    }

    @Test
    public void testShowPost () throws Exception {

        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(existingPost.getUser().getEmail())));
    }

    @Test
    public void testPostsIndex() throws Exception {
        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

    @Test
    public void testEditPost() throws Exception {
        Post existingPost = postDao.findAll().get(0);

        this.mvc.perform(
                post("/posts/edit/" + existingPost.getId()).with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "edited title")
                .param("body", "edited body"))
                .andExpect(status().is3xxRedirection()
        );

        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited body")));
    }

    @Test
    public void testDeletePost() throws Exception {

        this.mvc.perform(
                post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "post to be deleted")
                .param("body", "won't last long"))
                .andExpect(status().is3xxRedirection()
        );
        Post existingPost = postDao.getByTitle("post to be deleted");

        this.mvc.perform(
                post("/posts/delete/" + existingPost.getId()).with(csrf())
                .session((MockHttpSession) httpSession)
                .param("id", String.valueOf(existingPost.getId())))
                .andExpect(status().is3xxRedirection());

    }







}
