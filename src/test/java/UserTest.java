import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hsqldb.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import spring.model.AddressModel;
import spring.model.RoleModel;
import spring.model.UserModel;
import spring.service.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class UserTest{

	@Autowired
	Manager manager;

	private final String NAME_1 = "Jan";
	private final String SURNAME_1 = "Kowalski";

	private final String NAME_2 = "Tomasz";
	private final String SURNAME_2 = "Kot";
	
	private final long ADDRESS_ID_1 = 1;
	private final long ROLE_ID_1 = 1;
	
	private final long ADDRESS_ID_2 = 2;
	private final long ROLE_ID_2 = 1;
	
	boolean delete;
	
	private SessionFactory sessionFactory;
	private Session session = null;
	
	private List<Long>  addedUsers = new ArrayList<Long>();
	private List<Long>  addedAddreses = new ArrayList<Long>();
	private List<Long>  addedRoles = new ArrayList<Long>();
	
	@Test
	public void addUserCheck() {

		int n = manager.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress_id(ADDRESS_ID_1);
		user.setRole_id(ROLE_ID_1);

		manager.addUser(user);
		UserModel retrievedUser = manager.findUserById(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress_id());
		assertEquals(ROLE_ID_1, retrievedUser.getRole_id());

		assertEquals(n+1, manager.getAllUsers().size());
	}
	
	@Test
	public void deleteUserCheck(){
	
		int n = manager.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress_id(ADDRESS_ID_1);
		user.setRole_id(ROLE_ID_1);

		manager.addUser(user);
		
		UserModel retrievedUser = manager.findUserById(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress_id());
		assertEquals(ROLE_ID_1, retrievedUser.getRole_id());

		assertEquals(n+1, manager.getAllUsers().size());
		
		manager.deleteUser(user);
		assertEquals(n, manager.getAllUsers().size());
	}
	
	@Test 
	public void updateClientCheck(){
		int n = manager.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress_id(ADDRESS_ID_1);
		user.setRole_id(ROLE_ID_1);

		manager.addUser(user);
		
		UserModel retrievedUser = manager.findUserById(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress_id());
		assertEquals(ROLE_ID_1, retrievedUser.getRole_id());

		assertEquals(n+1, manager.getAllUsers().size());
		
		retrievedUser.setName(NAME_2);
		retrievedUser.setSurname(SURNAME_2);
		retrievedUser.setAddress_id(ADDRESS_ID_2);
		retrievedUser.setRole_id(ROLE_ID_2);
		manager.updateUser(retrievedUser);
		
		UserModel retrievedUser2 = manager.findUserById(retrievedUser);
		assertEquals(retrievedUser.getId(), retrievedUser2.getId());
		assertEquals(NAME_2, retrievedUser.getName());
		assertEquals(SURNAME_2, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_2, retrievedUser.getAddress_id());
		assertEquals(ROLE_ID_2, retrievedUser.getRole_id());
		
	}
	
}