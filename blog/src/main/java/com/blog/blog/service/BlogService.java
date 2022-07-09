package com.blog.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.blog.model.Blog;
import com.blog.blog.repo.BlogRepo;

@Service
public class BlogService {
	@Autowired
	private BlogRepo blogRepo;
	
	public Blog saveBlog(Blog blog) {
		blog.setOwner(getCurrentUserId());
		return blogRepo.save(blog);
	}
	
	public Blog getBlog(Long id) {
		Optional<Blog> blog = blogRepo.findById(id);
		if(blog.isPresent())return blog.get();
		return null;
	}
	
	public List<Blog> getAllBlog(){
		return blogRepo.findAll();
	}
	
	public void deleteBlog(Blog blog) {
		blogRepo.delete(blog);
	}
	
	public String getCurrentUserId() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public boolean canUpdateBlog(Authentication user,Blog blog) {
		return isOwner(user.getName(), blog) || isAdminUser(user);
	}
	private boolean isOwner(String user, Blog blog) {
		return user.equalsIgnoreCase(blog.getOwner());
	}
	
	private boolean isAdminUser(Authentication user) {
		return user.getAuthorities().stream().anyMatch(r-> r.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
	}
	
}
