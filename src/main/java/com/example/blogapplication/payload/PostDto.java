package com.example.blogapplication.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto  {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

    // Định nghĩa lớp DTO: DTO chứa các trường dữ liệu mà bạn muốn truyền giữa khác tầng.
    // DTO class không nên chưa logic business, chỉ nên chưa các filed và getter/setter
}
