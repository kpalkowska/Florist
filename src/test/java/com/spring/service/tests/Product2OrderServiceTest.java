package com.spring.service.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.Product2OrderModel;
import com.spring.model.ProductModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;
import com.spring.service.AddressService;
import com.spring.service.OrderService;
import com.spring.service.Product2OrderService;
import com.spring.service.ProductService;
import com.spring.service.RoleService;
import com.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
@Transactional("txManager")
public class Product2OrderServiceTest {
	
	@Autowired
	Product2OrderService poService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	private final String NAME_1 = "coś tam";
	private final String DESCRIPTION_1 = "pojedynczy";
	private final String PRICE_1 = "13,56";
	private final String TYPE_1 = "róża";
	private final String COLOR_1 = "czerwony";
	
	
	private final String NAME_2 = "coś tam2";
	private final String DESCRIPTION_2 = "bukiet";
	private final String PRICE_2 = "20,56";
	private final String TYPE_2 = "tulipan";
	private final String COLOR_2 = "żółty";
	
	private final String date = "03-06-2016";
	private final String date2 = "15-12-2016";
	
	private final String name = "Jan";
	private final String surname = "Kowalski";
	private final String login = "abc@abc.pl";
	private final String password = "123";

	private final String name2 = "Tomasz";
	private final String surname2 = "Kot";
	private final String login2 = "xyz@abc.pl";
	private final String password2 = "abcde";
	
	private final String zipCode = "82-300";
	private final String city = "Elblag";
	private final String street = "Kwiatowa";
	private final String number = "7";
	
	private final String zipCode2 = "83-500";
	private final String city2 = "Costam";
	private final String street2 = "Taka";
	private final String number2 = "9";
	
	private final String role1 = "Rola1";
	private final String role2 = "Rola2";
	
	@Rollback(true)
	@Test
	public void addProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
	
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);

		productService.addProduct(product);

		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);
		
		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, login, password, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setLogin(login);
		user.setPassword(password);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		OrderModel order = new OrderModel(date, user, address);
		order.setDate(date);
		order.setUsers(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel(order, product);
		
		po.setOrders(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrders());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
	}
	
	@Rollback(true)
	@Test
	public void deleteProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
		
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);

		productService.addProduct(product);

		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);
		
		addressService.addAddress(address);
		
		AddressModel address2 = new AddressModel(zipCode, city, street, number);
		address2.setZipCode(zipCode);
		address2.setCity(city);
		address2.setStreet(street);
		address2.setNumber(number);
		
		addressService.addAddress(address2);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, login, password, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setLogin(login);
		user.setPassword(password);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		OrderModel order = new OrderModel(date, user, address2);
		order.setDate(date);
		order.setUsers(user);
		order.setAddress(address2);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel(order, product);
		
		po.setOrders(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrders());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
		poService.deleteProduct2Order(po);
		assertEquals(n, poService.getAllProducts2Orders().size());
		
	}
	
	@Rollback(true)
	@Test
	public void updateProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
		
		
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);

		productService.addProduct(product);

		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);
		
		addressService.addAddress(address);
		
		RoleModel role = new RoleModel(role1);
		role.setRole(role1);
		
		roleService.addRole(role);
		
		UserModel user = new UserModel(name, surname, login, password, address, role);
		user.setName(name);
		user.setSurname(surname);
		user.setLogin(login);
		user.setPassword(password);
		user.setAddress(address);
		user.setRole(role);
		
		userService.addUser(user);
		
		OrderModel order = new OrderModel(date, user, address);
		order.setDate(date);
		order.setUsers(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel(order, product);
		
		po.setOrders(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrders());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
		
		ProductModel product2 = new ProductModel(NAME_2, DESCRIPTION_2, PRICE_2, TYPE_2, COLOR_2);
		product2.setName(NAME_2);
		product2.setDescription(DESCRIPTION_2);
		product2.setPrice(PRICE_2);
		product2.setType(TYPE_2);
		product2.setColor(COLOR_2);

		productService.addProduct(product2);

		AddressModel address2 = new AddressModel(zipCode2, city2, street2, number2);
		address2.setZipCode(zipCode2);
		address2.setCity(city2);
		address2.setStreet(street2);
		address2.setNumber(number2);
		
		addressService.addAddress(address2);
		
		RoleModel role3 = new RoleModel(role2);
		role.setRole(role2);
		
		roleService.addRole(role3);
		
		UserModel user2 = new UserModel(name2, surname2, login2, password2,address2, role3);
		user2.setName(name2);
		user2.setSurname(surname2);
		user2.setLogin(login2);
		user2.setPassword(password2);
		user2.setAddress(address2);
		user2.setRole(role3);
		
		userService.addUser(user2);
		
		OrderModel order2 = new OrderModel(date2, user2, address2);
		order2.setDate(date2);
		order2.setUsers(user2);
		order2.setAddress(address2);
		
		orderService.addOrder(order2);
		
		retrievedPO.setOrders(order2);
		retrievedPO.setProduct(product2);
		
		poService.updateProduct2Order(po);
		
		Product2OrderModel retrievedPO2 = poService.findProduct2Order(po);
		assertEquals(retrievedPO.getId(), retrievedPO2.getId());
		assertEquals(order2, retrievedPO2.getOrders());
		assertEquals(product2, retrievedPO2.getProduct());
	}
}
