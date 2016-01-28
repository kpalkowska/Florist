package ServiceTest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.model.AddressModel;
import spring.model.RoleModel;
import spring.model.UserModel;
import spring.service.AddressService;
import spring.service.RoleService;
import spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml" })
@Transactional("txManager")
public class UserServiceTest{

	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	RoleService roleService;

	private final String NAME_1 = "Jan";
	private final String SURNAME_1 = "Kowalski";

	private final String NAME_2 = "Tomasz";
	private final String SURNAME_2 = "Kot";
	
	private final String zipKode = "82-300";
	private final String city = "Elblag";
	private final String street = "Kwiatowa";
	private final String number = "7";
	
	private final String role_name = "Admin";
	
	private final String zipKode2 = "83-500";
	private final String city2 = "Costam";
	private final String street2 = "Taka";
	private final String number2 = "9";
	
	private final String role_name2 = "Nikt";
	
	@Rollback(false)
	@Test
	public void addUserCheck() {
		
		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role_name);
		role.setRole(role_name);
		
		roleService.addRole(role);

		int n = userService.getAllUsers().size();
		UserModel user = new UserModel(NAME_1, SURNAME_1, address, role);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(address);
		user.setRole(role);

		userService.addUser(user);
		UserModel retrievedUser = userService.findUser(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(address, retrievedUser.getAddress());
		assertEquals(role, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
	}
	
	@Rollback(false)
	@Test
	public void deleteUserCheck(){
	
		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role_name);
		role.setRole(role_name);
		
		roleService.addRole(role);

		int n = userService.getAllUsers().size();
		UserModel user = new UserModel(NAME_1, SURNAME_1, address, role);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(address);
		user.setRole(role);

		userService.addUser(user);
		UserModel retrievedUser = userService.findUser(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(address, retrievedUser.getAddress());
		assertEquals(role, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
		
		userService.deleteUser(user);
		assertEquals(n, userService.getAllUsers().size());
	}
	
	@Rollback(false)
	@Test 
	public void updateUserCheck(){

		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role_name);
		role.setRole(role_name);
		
		roleService.addRole(role);

		int n = userService.getAllUsers().size();
		UserModel user = new UserModel(NAME_1, SURNAME_1, address, role);
		user.setName(NAME_1);
		user.setSurname(SURNAME_1);
		user.setAddress(address);
		user.setRole(role);
		
		AddressModel address2 = new AddressModel(zipKode2, city2, street2, number2);
		address2.setZipKode(zipKode2);
		address2.setCity(city2);
		address2.setStreet(street2);
		address2.setNumber(number2);

		addressService.addAddress(address2);
		
		RoleModel role2 = new RoleModel(role_name2);
		role2.setRole(role_name2);
		
		roleService.addRole(role2);

		userService.addUser(user);
		UserModel retrievedUser = userService.findUser(user);
		assertEquals(user.getId(), retrievedUser.getId());
		assertEquals(NAME_1, retrievedUser.getName());
		assertEquals(SURNAME_1, retrievedUser.getSurname());
		assertEquals(address, retrievedUser.getAddress());
		assertEquals(role, retrievedUser.getRole());

		assertEquals(n+1, userService.getAllUsers().size());
		
		retrievedUser.setName(NAME_2);
		retrievedUser.setSurname(SURNAME_2);
		retrievedUser.setAddress(address2);
		retrievedUser.setRole(role2);
		userService.updateUser(retrievedUser);
		
		UserModel retrievedUser2 = userService.findUser(user);
		assertEquals(retrievedUser.getId(), retrievedUser2.getId());
		assertEquals(NAME_2, retrievedUser2.getName());
		assertEquals(SURNAME_2, retrievedUser2.getSurname());
		assertEquals(address2, retrievedUser2.getAddress());
		assertEquals(role2, retrievedUser2.getRole());
		
	}
	
}