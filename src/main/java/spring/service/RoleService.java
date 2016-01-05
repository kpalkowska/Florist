package spring.service;

import spring.model.RoleModel;
import java.util.List;

import javax.management.relation.Role;



public interface RoleService {
	 
    public RoleModel getRole(int id);
    void addRole(RoleModel role);
	List<RoleModel> getAllRoles();
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);

}