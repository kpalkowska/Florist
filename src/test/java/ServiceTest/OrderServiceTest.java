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
import spring.model.UserModel;
import spring.service.AddressService;
import spring.service.OrderService;
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
	
	@Rollback(false)
	@Test
	public void addOrderCheck(){
		
		AddressModel address = new AddressModel();
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		UserModel user = new UserModel();
		user.setName(name);
		user.setSurname(surname);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(address, retrievedOrder.getAddress());
		assertEquals(user, retrievedOrder.getUser());

		assertEquals(n+1, orderService.getAllOrders().size());
	}
	
	@Rollback(false)
	@Test
	public void deleteOrderCheck(){
		
		AddressModel address = new AddressModel();
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		UserModel user = new UserModel();
		user.setName(name);
		user.setSurname(surname);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(address, retrievedOrder.getAddress());
		assertEquals(user, retrievedOrder.getUser());

		assertEquals(n+1, orderService.getAllOrders().size());
		
		orderService.deleteOrder(order);
		assertEquals(n, orderService.getAllOrders().size());
	}
	
	@Rollback(false)
	@Test
	public void updateOrderCheck(){
		
		AddressModel address = new AddressModel();
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		UserModel user = new UserModel();
		user.setName(name);
		user.setSurname(surname);
		
		userService.addUser(user);
		
		int n = orderService.getAllOrders().size();
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		OrderModel retrievedOrder = orderService.findOrder(order);
		assertEquals(order.getId(), retrievedOrder.getId());
		assertEquals(date, retrievedOrder.getDate());
		assertEquals(address, retrievedOrder.getAddress());
		assertEquals(user, retrievedOrder.getUser());

		assertEquals(n+1, orderService.getAllOrders().size());
		
		AddressModel address2 = new AddressModel();
		address2.setZipKode(zipKode2);
		address2.setCity(city2);
		address2.setStreet(street2);
		address2.setNumber(number2);

		addressService.addAddress(address2);
		
		UserModel user2 = new UserModel();
		user2.setName(name2);
		user2.setSurname(surname2);
		
		userService.addUser(user2);
		
		retrievedOrder.setDate(date2);
		retrievedOrder.setUser(user2);
		retrievedOrder.setAddress(address2);
		orderService.updateOrder(retrievedOrder);
		
		OrderModel retrievedOrder2 = orderService.findOrder(order);
		assertEquals(retrievedOrder.getId(), retrievedOrder2.getId());
		assertEquals(date2, retrievedOrder2.getDate());
		assertEquals(address2, retrievedOrder2.getAddress());
		assertEquals(user2, retrievedOrder2.getUser());

	}

}
