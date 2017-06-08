package com.luke.user.model;

import java.util.List;

public interface RoleDao {
	
	Role getRole(String role);
	
	List<Role> getAllRoles();
	

}
