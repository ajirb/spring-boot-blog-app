package com.blog.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.model.Role;
import com.blog.blog.repo.RoleRepo;


@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public Role findByName(String name) {
		Role role = roleRepo.findByName(name);
		return role;
	}
}
