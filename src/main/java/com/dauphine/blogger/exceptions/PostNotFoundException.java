package com.dauphine.blogger.exceptions;

import java.util.UUID;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(UUID id) {
        super("Post not found with id: " + id);
    }
}