package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "Endpoints for managing categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public List<Category> retrieveAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Get all posts of a category")
    public List<Post> retrievePostsByCategoryId(@PathVariable UUID id) {
        return postService.getAllByCategoryId(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest request) {
        return categoryService.create(request.getName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category")
    public Category updateCategory(@PathVariable UUID id,
                                   @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateName(id, request.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public boolean deleteCategory(@PathVariable UUID id) {
        return categoryService.deleteById(id);
    }
}