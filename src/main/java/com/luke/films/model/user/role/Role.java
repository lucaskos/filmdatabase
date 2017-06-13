package com.luke.films.model.user.role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.luke.films.model.user.User;

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

	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="users_roles",
        joinColumns = {@JoinColumn(name="users_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="roles_id", referencedColumnName="id")}
    )
	private java.util.Set<User> userRoles;

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
		return userRoles;
	}

	public void setUsersSet(java.util.Set<User> usersSet) {
		this.userRoles = usersSet;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role +  "]";
	}


}
