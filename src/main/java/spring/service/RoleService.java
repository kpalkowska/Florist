package spring.service;

import spring.model.RoleModel;
import java.util.List;

public interface RoleService {
	 
    public RoleModel getRole(int id);
    void addRole(RoleModel role);
	List<RoleModel> getAllRoles();
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);

}