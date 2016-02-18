package com.spring.dao;

import java.util.List;

import com.spring.model.RoleModel;

public interface RoleDAO {
	 
    public RoleModel getRole(int id);
	
	List<RoleModel> getAllRoles();
	void addRole(RoleModel role);
	RoleModel findRoleByName(String name);
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
    
}
