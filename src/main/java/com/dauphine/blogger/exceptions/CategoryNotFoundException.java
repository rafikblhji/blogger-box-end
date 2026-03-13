package com.dauphine.blogger.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(UUID id) {
        super("Category not found with id: " + id);
    }
}