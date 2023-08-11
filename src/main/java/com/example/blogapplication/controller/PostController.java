package com.example.blogapplication.controller;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    // If configuring a class as a spring bean and it has only one constructor, we can omit @Autowired annotation

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
}
