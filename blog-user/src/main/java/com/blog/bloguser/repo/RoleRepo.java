package com.blog.bloguser.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.bloguser.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
