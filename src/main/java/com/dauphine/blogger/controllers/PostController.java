package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "Endpoints for managing posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id")
    public Post retrievePostById(@PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest request) {
        return postService.create(request.getTitle(), request.getContent(), request.getCategoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(@PathVariable UUID id,
                           @RequestBody UpdatePostRequest request) {
        return postService.update(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public boolean deletePost(@PathVariable UUID id) {
        return postService.deleteById(id);
    }
}