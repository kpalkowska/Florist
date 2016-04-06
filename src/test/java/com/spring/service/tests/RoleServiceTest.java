package com.spring.service.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.RoleModel;
import com.spring.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
@Transactional("txManager")
public class RoleServiceTest {
	
	@Autowired
	RoleService roleService;

	private final String ROLE_1 = "Rola8";
	private final String ROLE_2 = "Rola9";
		
	@Rollback(true)
	@Test
	public void addProductCheck() {

		int n = roleService.getAllRoles().size();
		RoleModel role = new RoleModel(ROLE_1);
		role.setRole(ROLE_1);
		
		roleService.addRole(role);

		RoleModel retrievedRole = roleService.findRole(role);
		assertEquals(role.getId(), retrievedRole.getId());
		assertEquals(ROLE_1, retrievedRole.getRole());
		
		assertEquals(n+1, roleService.getAllRoles().size());
	}
	
	@Rollback(true)
	@Test
	public void deleteProductCheck(){
	
		int n = roleService.getAllRoles().size();
		RoleModel role = new RoleModel(ROLE_1);
		role.setRole(ROLE_1);
		
		roleService.addRole(role);

		RoleModel retrievedRole = roleService.findRole(role);
		assertEquals(role.getId(), retrievedRole.getId());
		assertEquals(ROLE_1, retrievedRole.getRole());
		
		assertEquals(n+1, roleService.getAllRoles().size());
		
		roleService.deleteRole(role);
		assertEquals(n, roleService.getAllRoles().size());
	}
	
	@Rollback(true)
	@Test 
	public void updateProductCheck(){
		int n = roleService.getAllRoles().size();
		RoleModel role = new RoleModel(ROLE_1);
		role.setRole(ROLE_1);
		
		roleService.addRole(role);

		RoleModel retrievedRole = roleService.findRole(role);
		assertEquals(role.getId(), retrievedRole.getId());
		assertEquals(ROLE_1, retrievedRole.getRole());
		
		assertEquals(n+1, roleService.getAllRoles().size());
		
		
		retrievedRole.setRole(ROLE_2);
		

		roleService.updateRole(retrievedRole);
	
		RoleModel retrievedRole2 = roleService.findRole(role);
		assertEquals(role.getId(), retrievedRole2.getId());
		assertEquals(ROLE_2, retrievedRole2.getRole());
		
	}
	

}
