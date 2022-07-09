package com.blog.blog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.Userdto;
import com.blog.blog.model.Role;
import com.blog.blog.model.User;
import com.blog.blog.repo.UserRepo;

@Service(value = "userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private RoleService roleService;
	
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user==null) throw new UsernameNotFoundException("Invalid username or password.");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
		return authorities;
	}
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(users::add);
		return users;
	}
	
	public User findByUserName(String userName) {
		return userRepo.findByUsername(userName);
	}
	
	public User save(Userdto user) {
		System.out.println(user);
		if(isExistsUserName(user.getUsername())) {
			//todo throw exception for username exists
			System.out.println("Username already exists");
			return null;
		}
		
		User usr = new User();
		usr.setUsername(user.getUsername());
		usr.setTitle(user.getTitle());
		usr.setPassword(bcryptEncoder.encode(user.getPassword()));
		Role role = roleService.findByName("USER");
		
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		
		if(user.getTitle().equalsIgnoreCase("Admin")) roleSet.add(roleService.findByName("ADMIN"));
		usr.setRoles(roleSet);
		
		return userRepo.save(usr);
	}
	
	private boolean isExistsUserName(String userName) {
		User usr = userRepo.findByUsername(userName);
		return usr!=null;
	}
}
