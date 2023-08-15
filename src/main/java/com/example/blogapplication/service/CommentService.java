package com.example.blogapplication.service;

import com.example.blogapplication.payload.CommentDto;
import com.example.blogapplication.payload.PostDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
