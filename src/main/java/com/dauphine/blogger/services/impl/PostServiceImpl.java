package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;
    private final CategoryService categoryService;

    public PostServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.temporaryPosts = new ArrayList<>();
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> post.getCategory() != null
                        && categoryId.equals(post.getCategory().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        Post post = new Post(UUID.randomUUID(), title, content, category);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(post -> id.equals(post.getId()));
    }
}