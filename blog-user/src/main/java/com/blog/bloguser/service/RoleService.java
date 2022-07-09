package com.blog.bloguser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.bloguser.model.Role;
import com.blog.bloguser.repo.RoleRepo;

@Service
public class RoleService {

	@Autowired
	RoleRepo roleRepo;
	
	public Role findByName(String name) {
		Role role = roleRepo.findByName(name);
		return role;
	}
}
