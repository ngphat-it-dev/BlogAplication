package com.example.blogapplication.service.impl;

import com.example.blogapplication.entity.Comment;
import com.example.blogapplication.entity.Post;
import com.example.blogapplication.exception.ResourceNotFoundException;
import com.example.blogapplication.payload.CommentDto;
import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.repository.CommentRepository;
import com.example.blogapplication.repository.PostRepository;
import com.example.blogapplication.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        // set post to comment entity
        comment.setPost(post);

        //save comment entity to DB
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment Dto
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    // Convert Entity into DTO
    private CommentDto mapToDTO(Comment comment){

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }
    // convert DTO to Entity
    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}
