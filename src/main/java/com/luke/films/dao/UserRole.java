package com.luke.films.dao;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class UserRole {
	
	@Id
	@GeneratedValue()
	private int id;
	
	@Column(name="role")
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;
	
	public UserRole() {
		
	}
	
	public UserRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
