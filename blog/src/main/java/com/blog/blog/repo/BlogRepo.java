package com.blog.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog.model.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
	
}
