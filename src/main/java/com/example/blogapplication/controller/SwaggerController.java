package com.example.blogapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/swagger")
    public String redirectToSwaggerUi() {
        return "redirect:/swagger-ui.html"; // Chuyển hướng đến trang Swagger UI
    }
}
