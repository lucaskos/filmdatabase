package com.luke.films.model.user.role;

import java.util.List;

import com.luke.films.model.user.User;

public interface RoleDao {
	
	Role getRole(String role);
	
	List<Role> getAllRoles();

}
