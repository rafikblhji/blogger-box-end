package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundException;
import com.dauphine.blogger.exceptions.PostNotFoundException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Post>> retrieveAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id")
    public ResponseEntity<Post> retrievePostById(@PathVariable UUID id)
            throws PostNotFoundException {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> createPost(@RequestBody CreationPostRequest request)
            throws CategoryNotFoundException {
        Post post = postService.create(request.getTitle(), request.getContent(), request.getCategoryId());
        return ResponseEntity.created(URI.create("v1/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id,
                                           @RequestBody UpdatePostRequest request) throws PostNotFoundException {
        return ResponseEntity.ok(postService.update(id, request.getTitle(), request.getContent()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}