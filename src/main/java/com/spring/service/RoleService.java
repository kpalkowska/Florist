package com.spring.service;

import java.util.List;

import com.spring.model.RoleModel;

public interface RoleService {
	 
    void addRole(RoleModel role);
	List<RoleModel> getAllRoles();
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
	RoleModel findRole(RoleModel role);
	RoleModel exists(String name);
}