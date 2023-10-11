package com.example.blogapplication.controller;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.payload.PostResponse;
import com.example.blogapplication.service.PostService;
import com.example.blogapplication.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    // If configuring a class as a spring bean & it has only one constructor, we can omit @Autowired annotation

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @PostMapping()
    public EntityModel<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        EntityModel<PostDto> entityModel = EntityModel.of(createdPost);

        // Đính kèm liên kết HATEOAS cho phương thức
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class)
                .createPost(postDto)).withSelfRel();
        entityModel.add(selfLink);

        return entityModel;
    }

    // get all posts rest api
    @Operation(
            summary = "Get all posts",
            description = "This endpoint retrieves all posts with optional pagination and sorting. " +
                    "You can specify page number, page size, sorting field, and sorting direction."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Error while retrieving posts")
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public CollectionModel<EntityModel<PostDto>> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PostResponse postResponse = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        // Tạo danh sách EntityModel và đính kèm liên kết HATEOAS cho từng tài nguyên
        List<EntityModel<PostDto>> entityModels = postResponse.getContent().stream()
                .map(postDto -> {
                    EntityModel<PostDto> entityModel = EntityModel.of(postDto);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class)
                                    .getPostById(postDto.getId()))
                            .withSelfRel();
                    entityModel.add(selfLink);
                    return entityModel;
                })
                .collect(Collectors.toList());

        Link selfLink = WebMvcLinkBuilder.linkTo(PostController.class).withSelfRel();
        CollectionModel<EntityModel<PostDto>> collectionModel = CollectionModel.of(entityModels);

        return collectionModel;
    }

    // get post by id api
    @Operation(
            summary = "Get a post by ID",
            description = "This endpoint retrieves a post by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Error while retrieving the post")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PostDto>> getPostById(@PathVariable(name = "id") long id){
        PostDto post = postService.getPostById((id));
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class)
                        .createPost(post))
                .withSelfRel();

        // Wrap the PostDto in an EntityModel and add the self-link
        EntityModel<PostDto> entityModel = EntityModel.of(post, selfLink);

        return ResponseEntity.ok(entityModel);
    }

    // update post by id api
    @Operation(
            summary = "Update a post by ID",
            description = "This endpoint allows you to update a post by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Error while updating the post")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PostDto>> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class)
                        .getPostById(postResponse.getId()))
                .withSelfRel();

        // Đính kèm bài viết đã được cập nhật cùng với liên kết tự tham chiếu vào EntityModel
        EntityModel<PostDto> entityModel = EntityModel.of(postDto, selfLink);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    // delete post by id api
    @Operation(
            summary = "Delete a post by ID",
            description = "This endpoint allows you to delete a post by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Error while deleting the post")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted succesfully.", HttpStatus.OK);
    }

}
