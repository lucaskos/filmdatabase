package com.luke.films.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@Size(min=5, max=45)
	@Column(name="username")
	private String username;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	
	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@NotBlank
	@NotEmpty
	@Email
	@Column(name="email")
	private String email;

	public User() {

	}

	public User(String username, String password, String email) {
		super();
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", email=" + email
				+ "]";
	}

}
