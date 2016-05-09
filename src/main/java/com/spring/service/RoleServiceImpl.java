package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.RoleDAO;
import com.spring.model.RoleModel;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public void deleteRole(RoleModel role) {
		roleDAO.deleteRole(role);
	}

	@Override
	public List<RoleModel> getAllRoles() {
		return roleDAO.getAllRoles();
	}

	@Override
	public void updateRole(RoleModel role) {
		roleDAO.updateRole(role);
	}

	@Override
	public void addRole(RoleModel role) {
		roleDAO.addRole(role);
	}

	@Override
	public RoleModel findRole(RoleModel role) {
		return roleDAO.findRole(role);
	}

	@Override
	public RoleModel exists(String role) {
		return roleDAO.existed(role);
	}
}
