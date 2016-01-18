package spring.dao;

import spring.model.RoleModel;
import java.util.List;

public interface RoleDAO {
	 
    public RoleModel getRole(int id);
	
	List<RoleModel> getAllRoles();
	void addRole(RoleModel role);
	RoleModel findRoleByName(String name);
	void deleteRole(RoleModel role);
	void updateRole(RoleModel role);
    
}
