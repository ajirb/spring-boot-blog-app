package com.blog.bloguser.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.bloguser.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
