package com.spring.dao;

import java.util.List;

import com.spring.model.RoleModel;

public interface RoleDAO {

	List<RoleModel> getAllRoles();
	void addRole(RoleModel role);
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
	public boolean exists(String name);
    
}
