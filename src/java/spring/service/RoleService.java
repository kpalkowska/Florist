package java.spring.service;

import java.util.List;

import javax.management.relation.Role;

import org.hsqldb.rights.Role;

public interface RoleService {
	 
    public Role getRole(int id);
    void addRole(Role role);
	List<Role> getAllRoles();
	void deleteRole(Role role);
	void updateRole(Role role);

}