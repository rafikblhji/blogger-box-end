package com.dauphine.blogger.repository;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
            SELECT post FROM Post post
            WHERE UPPER(post.title) LIKE UPPER(CONCAT('%', :value, '%'))
            OR UPPER(post.content) LIKE UPPER(CONCAT('%', :value, '%'))
            """)
    List<Post> findAllLikeValue(@Param("value") String value);

    @Query("""
            SELECT post FROM Post post
            WHERE post.category.id = :categoryId
            """)
    List<Post> findAllByCategoryId(@Param("categoryId") UUID categoryId);
}