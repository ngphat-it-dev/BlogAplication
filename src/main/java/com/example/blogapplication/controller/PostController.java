package com.example.blogapplication.controller;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    // get post by id api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById((id)));
    }
}
