package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryNotFoundException;
import com.dauphine.blogger.exceptions.PostNotFoundException;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByCategoryId(UUID categoryId);
    Post getById(UUID id) throws PostNotFoundException;
    Post create(String title, String content, UUID categoryId) throws CategoryNotFoundException;
    Post update(UUID id, String title, String content) throws PostNotFoundException;
    boolean deleteById(UUID id);
}