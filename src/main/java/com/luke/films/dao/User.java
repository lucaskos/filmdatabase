package com.luke.films.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 45)
	@Column(name = "username")
	private String username;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@NotBlank
	@NotEmpty
	@Email
	@Column(name = "email")
	private String email;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Collection<UserRole> users_roles;

	public User() {

	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<UserRole> getUsers_roles() {
		return users_roles;
	}

	public void setUsers_roles(Collection<UserRole> users_roles) {
		this.users_roles = users_roles;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", password=" + password + ", enabled=" + enabled
				+ ", email=" + email + ", users_roles=" + users_roles + "]";
	}




}
