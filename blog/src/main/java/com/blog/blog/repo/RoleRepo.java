package com.blog.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
