package com.spring.dao;

import java.util.List;

import com.spring.model.RoleModel;

public interface RoleDAO {

	List<RoleModel> getAllRoles();

	void addRole(RoleModel role);

	void deleteRole(RoleModel role);

	void updateRole(RoleModel role);

	boolean exists(String name);

	RoleModel findRole(RoleModel role);

	RoleModel existed(String role);

}
