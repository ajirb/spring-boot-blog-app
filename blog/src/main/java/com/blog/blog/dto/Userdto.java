package com.blog.blog.dto;

public class Userdto {
	private String username;
    private String password;
    private String title;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Userdto() {
		super();
	}
	@Override
	public String toString() {
		return "Userdto [username=" + username + ", password=" + password + ", title=" + title + "]";
	}
}
