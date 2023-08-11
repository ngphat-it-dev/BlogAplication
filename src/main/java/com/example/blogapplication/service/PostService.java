package com.example.blogapplication.service;

import com.example.blogapplication.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
