package com.luke.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "role")
	private String role;

	// mapped to userRoles in the user.class

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersRoles")
	private java.util.Set<User> usersSet;

	public Role() {

	}

	public Role(String role) {
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

	public java.util.Set<User> getUsersSet() {
		return usersSet;
	}

	public void setUsersSet(java.util.Set<User> usersSet) {
		this.usersSet = usersSet;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role +  "]";
	}


}
