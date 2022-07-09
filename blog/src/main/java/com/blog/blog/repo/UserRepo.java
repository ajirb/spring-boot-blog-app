package com.blog.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
