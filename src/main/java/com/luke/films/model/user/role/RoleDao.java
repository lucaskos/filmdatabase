package com.luke.films.model.user.role;

import java.util.List;

public interface RoleDao {
	
	Role getRole(String role);
	
	List<Role> getAllRoles();

}
