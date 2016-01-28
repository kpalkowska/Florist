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
import spring.model.OrderModel;
import spring.model.RoleModel;
import spring.model.UserModel;
import spring.service.AddressService;
import spring.service.OrderService;
import spring.service.RoleService;
import spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml" })
@Transactional("txManager")
public class OrderServiceTest {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	private final String date = "03-06-2016";
	private final String date2 = "15-12-2016";
	
	private final String name = "Jan";
	private final String surname = "Kowalski";

	private final String name2 = "Tomasz";
	private final String surname2 = "Kot";
	
	private final String zipKode = "82-300";
	private final String city = "Elblag";
	private final String street = "Kwiatowa";
	private final String number = "7";
	
	private final String zipKode2 = "83-500";
	private final String city2 = "Costam";
	private final String street2 = "Taka";
	private final String number2 = "9";
	
	private final String role1 = "admin";
	private final String role2 = "pracownik";
	
	@Rollback(false)
	@Test
	public void addOrderCheck(){
		
		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel(date, user);
		order.setDate(date);
		order.setUsers(user);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(user, retrievedOrder.getUsers());

		assertEquals(n+1, orderService.getAllOrders().size());
	}
	
	@Rollback(false)
	@Test
	public void deleteOrderCheck(){
		
		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel(date, user);
		order.setDate(date);
		order.setUsers(user);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(user, retrievedOrder.getUsers());

		assertEquals(n+1, orderService.getAllOrders().size());
		
		orderService.deleteOrder(order);
		assertEquals(n, orderService.getAllOrders().size());
	}
	
	@Rollback(false)
	@Test
	public void updateOrderCheck(){
		
		AddressModel address = new AddressModel(zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel(date, user, address);
		order.setDate(date);
		order.setUsers(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(address, retrievedOrder.getAddress());
		assertEquals(user, retrievedOrder.getUsers());

		assertEquals(n+1, orderService.getAllOrders().size());
		
		AddressModel address2 = new AddressModel(zipKode, city, street, number);
		address2.setZipKode(zipKode2);
		address2.setCity(city2);
		address2.setStreet(street2);
		address2.setNumber(number2);

		addressService.addAddress(address2);
		
		RoleModel role3 = new RoleModel(role2);
		role3.setRole(role2);
		
		roleService.addRole(role);
		
		UserModel user2 = new UserModel(name2, surname2, address2, role3);
		user2.setName(name);
		user2.setSurname(surname);
		user2.setAddress(address);
		user2.setRole(role3);
		
		userService.addUser(user2);
		
		retrievedOrder.setDate(date2);
		retrievedOrder.setUsers(user2);
		retrievedOrder.setAddress(address2);
		orderService.updateOrder(retrievedOrder);
		
		OrderModel retrievedOrder2 = orderService.findOrder(order);
		assertEquals(retrievedOrder.getId(), retrievedOrder2.getId());
		assertEquals(date2, retrievedOrder2.getDate());
		assertEquals(address2, retrievedOrder2.getAddress());
		assertEquals(user2, retrievedOrder2.getUsers());

	}

}
