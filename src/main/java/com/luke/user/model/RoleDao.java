package com.luke.user.model;

import java.util.List;

public interface RoleDao {
	
	void addRole(String name);
	
	void deleteRole(String name);
	
	List<Role> getAllRoles();
	
	

}
