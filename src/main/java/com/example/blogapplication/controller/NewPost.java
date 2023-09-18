package com.example.blogapplication.controller;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewPost {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public String handleSubmit(@RequestBody PostDto postDto, Model model) {
        PostDto newPost = postService.createPost(postDto);
        model.addAttribute("data", newPost);
        System.out.println("---------------"+newPost);

        return "NewFeeds";
    }
}
