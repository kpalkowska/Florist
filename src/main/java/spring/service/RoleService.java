package spring.service;

import spring.model.RoleModel;
import java.util.List;

public interface RoleService {
	 
    void addRole(RoleModel role);
	List<RoleModel> getAllRoles();
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
	RoleModel findRole(RoleModel role);
}