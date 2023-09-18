package com.example.blogapplication.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
// @Data + @AllArgsConstructor Auto genarated Getter, Setter & Constructor
@Entity
//@Entity annotations to map over JPA model with MySQL database table
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)

//@Table to give a name to the table, so it has a name, name attribute

public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the post")
    private Long id;

    @Column(name = "title",nullable = false)
    @Schema(description = "Title of the post")
    private String title;

    @Column(name = "description",nullable = false)
    @Schema(description = "Description of the post")
    private String description;

    @Column(name = "content",nullable = false)
    @Schema(description = "Content of the post")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Comments associated with the post")
    private Set<Comment> comments = new HashSet<>();
}
