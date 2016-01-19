import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hsqldb.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import spring.model.UserModel;
import spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class UserServiceTest{

	@Autowired
//	Manager manager;
	UserService userService;

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

		int n = userService.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(ADDRESS_ID_1);
		user.setRole(ROLE_ID_1);

		long id = 1;
		userService.addUser(user);
		UserModel retrievedUser = userService.findUserById(id);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress());
		assertEquals(ROLE_ID_1, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
	}
	
	@Test
	public void deleteUserCheck(){
	
		int n = userService.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(ADDRESS_ID_1);
		user.setRole(ROLE_ID_1);

		userService.addUser(user);
		
		int id =1;
		UserModel retrievedUser = userService.findUserById(id);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress());
		assertEquals(ROLE_ID_1, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
		
		userService.deleteUser(user);
		assertEquals(n, userService.getAllUsers().size());
	}
	
	@Test 
	public void updateClientCheck(){
		int n = userService.getAllUsers().size();
		UserModel user = new UserModel( NAME_1, SURNAME_1, ADDRESS_ID_1, ROLE_ID_1);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(ADDRESS_ID_1);
		user.setRole(ROLE_ID_1);

		userService.addUser(user);
		
		long id = 1;
		UserModel retrievedUser = userService.findUserById(id);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_1, retrievedUser.getAddress());
		assertEquals(ROLE_ID_1, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
		
		retrievedUser.setName(NAME_2);
		retrievedUser.setSurname(SURNAME_2);
		retrievedUser.setAddress(ADDRESS_ID_2);
		retrievedUser.setRole(ROLE_ID_2);
		userService.updateUser(retrievedUser);
		
		
		long id2 =2;
		UserModel retrievedUser2 = userService.findUserById(id2);
		assertEquals(retrievedUser.getId(), retrievedUser2.getId());
		assertEquals(NAME_2, retrievedUser.getName());
		assertEquals(SURNAME_2, retrievedUser.getSurname());
		assertEquals(ADDRESS_ID_2, retrievedUser.getAddress());
		assertEquals(ROLE_ID_2, retrievedUser.getRole());
		
	}
	
}