package java.spring.dao;

import javax.management.relation.Role;
import java.spring.model.*;
import java.util.List;

public interface RoleDAO {
	 
    public RoleModel getRole(int id);
	
	List<RoleModel> getAllRoles();
	void addRole(RoleModel role);
	RoleModel findRoleByName(String name);
	void deleteUser(RoleModel role);
	void updateUser(RoleModel role);
    
}
