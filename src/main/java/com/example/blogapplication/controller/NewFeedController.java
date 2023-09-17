package com.example.blogapplication.controller;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.payload.PostResponse;
import com.example.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/newfeeds")
public class NewFeedController {

    @Autowired
    private PostService postService;
    @GetMapping("/")
    public String viewPost(@RequestParam(name = "pageNo") int pageNo, Model model) {
       PostResponse posts = postService.getAllPosts(pageNo,3,"id", "asc");

        System.out.println(posts);

        model.addAttribute("posts",posts.getContent());
        model.addAttribute("currentPage",posts.getPageNo());
        model.addAttribute("totalPages",posts.getTotalPages());
        model.addAttribute("totalItems",posts.getTotalElements());
        model.addAttribute("pageSize",posts.getPageSize());
        return "NewFeeds";
    }

    @GetMapping()
    public String viewHomePage(Model model){
        return viewPost(0,model);
    }

}
