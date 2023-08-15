package com.example.blogapplication.service;

import com.example.blogapplication.payload.CommentDto;
import com.example.blogapplication.payload.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId,  Long commentId);
}
