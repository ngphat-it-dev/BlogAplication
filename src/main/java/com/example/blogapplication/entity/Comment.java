package com.example.blogapplication.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the comment")
    private long id;

    @Schema(description = "Name of the commenter")
    private String name;

    @Schema(description = "Email of the commenter")
    private String email;

    @Schema(description = "Body of the comment")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @Schema(description = "The associated post for this comment")
    private Post post;

    // Constructors, getters, setters, and other methods
}
