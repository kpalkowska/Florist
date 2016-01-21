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
import spring.model.Product2OrderModel;
import spring.model.ProductModel;
import spring.model.UserModel;
import spring.service.AddressService;
import spring.service.OrderService;
import spring.service.Product2OrderService;
import spring.service.ProductService;
import spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml" })
@Transactional("txManager")
public class Product2OrderServiceTest {
	
	@Autowired
	Product2OrderService poService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	private final String name1 = "nowy";
	private final String description1 = "nowy";
	private final String price1 = "13,56";

	private final String name0 = "nowszy";
	private final String description = "nowszy";
	private final String price = "20,56";
	
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
	public void addProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
		
		ProductModel product = new ProductModel();
		product.setName(name1);
		product.setDescription(description1);
		product.setPrice(price1);

		productService.addProduct(product);

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
		
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel();
		
		po.setOrder(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrder());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
	}
	
	@Rollback(false)
	@Test
	public void deleteProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
		
		ProductModel product = new ProductModel();
		product.setName(name1);
		product.setDescription(description1);
		product.setPrice(price1);

		productService.addProduct(product);

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
		
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel();
		
		po.setOrder(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrder());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
		poService.deleteProduct2Order(po);
		assertEquals(n, poService.getAllProducts2Orders().size());
		
	}
	
	@Rollback(false)
	@Test
	public void updateProduct2OrderCheck(){
		
		int n = poService.getAllProducts2Orders().size();
		
		ProductModel product = new ProductModel();
		product.setName(name1);
		product.setDescription(description1);
		product.setPrice(price1);

		productService.addProduct(product);

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
		
		OrderModel order = new OrderModel();
		order.setDate(date);
		order.setUser(user);
		order.setAddress(address);
		
		orderService.addOrder(order);
		
		Product2OrderModel po = new Product2OrderModel();
		
		po.setOrder(order);
		po.setProduct(product);
		
		poService.addProduct2Order(po);
		
		Product2OrderModel retrievedPO = poService.findProduct2Order(po);
		assertEquals(po.getId(), retrievedPO.getId());
		assertEquals(order, retrievedPO.getOrder());
		assertEquals(product, retrievedPO.getProduct());
		
		assertEquals(n+1, poService.getAllProducts2Orders().size());
		
		ProductModel product2 = new ProductModel();
		product2.setName(name0);
		product2.setDescription(description);
		product2.setPrice(price);

		productService.addProduct(product2);

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
		
		OrderModel order2 = new OrderModel();
		order2.setDate(date2);
		order2.setUser(user2);
		order2.setAddress(address2);
		
		orderService.addOrder(order2);
		
		retrievedPO.setOrder(order2);
		retrievedPO.setProduct(product2);
		
		poService.updateProduct2Order(po);
		
		Product2OrderModel retrievedPO2 = poService.findProduct2Order(po);
		assertEquals(retrievedPO.getId(), retrievedPO2.getId());
		assertEquals(order2, retrievedPO2.getOrder());
		assertEquals(product2, retrievedPO2.getProduct());
	}
}
