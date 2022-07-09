package com.blog.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.Blog;
import com.blog.blog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<?> createBlog(@RequestBody Blog blog){
		Blog blg = blogService.saveBlog(blog);
		return ResponseEntity.ok(blg);
	}
	
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id){
		Blog blog = blogService.getBlog(id);
		return ResponseEntity.ok(blog);
	}
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		List<Blog> blogs = blogService.getAllBlog();
		return ResponseEntity.ok(blogs);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Blog blog = blogService.getBlog(id);
		if(null == blog) return new ResponseEntity<String>("Failed to update",HttpStatus.BAD_REQUEST);
		if(blogService.canUpdateBlog(SecurityContextHolder.getContext().getAuthentication(), blog)) {
			blogService.deleteBlog(blog);
			return new ResponseEntity<String>("Deleted",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Failed to delete",HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog){
		Blog blg = blogService.getBlog(blog.getId());
		if(null==blg )return new ResponseEntity<String>("Failed to update",HttpStatus.BAD_REQUEST);
		if(blogService.canUpdateBlog(SecurityContextHolder.getContext().getAuthentication(), blog)) {
			blg = blogService.saveBlog(blog);
			return new ResponseEntity<Blog>(blg,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Failed to update",HttpStatus.UNAUTHORIZED);
	}
}
