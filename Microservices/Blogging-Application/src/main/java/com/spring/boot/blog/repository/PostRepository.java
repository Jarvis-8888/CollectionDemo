package com.spring.boot.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	// List of post of particular User
	List<Post> findByUser(User user);
	
	// List of post of particular Category
	List<Post> findByCategory(Category category);
	
	// List of post of particular pattern
	List<Post> findByTitleContaining(String pattern);

}
