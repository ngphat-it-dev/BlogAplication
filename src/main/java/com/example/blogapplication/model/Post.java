package com.example.blogapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
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
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "content",nullable = false)
    private String content;
}
