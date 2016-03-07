package ServiceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:security-context.xml"})
@Transactional("txManager")
public class FindProductTest {


	/* 
	
	inserty do pustej bazy
	
	
	INSERT INTO Products VALUES('rose', '30 roses with bow', '200', 'bouquet', 'red', null);
	INSERT INTO Products VALUES('rose', '6 roses with bow', '2.99', 'bouquet', 'pink', null);
	INSERT INTO Products(name, price, type, color, foto) VALUES('rose', '5', 'single', 'red', null);
	INSERT INTO Products VALUES('tulip', '12 tulips with ribbon and pearls', '50', 'bouquet', 'red', null);
	INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'pink', null);
	INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'white', null);
	INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'red', null);
	INSERT INTO Products VALUES('violet', 'violet composition', '120', 'potted', 'multicolour', null);
	INSERT INTO Products VALUES('hyacinth', 'hyacith composition', '107', 'potted', 'multicolour', null);
	INSERT INTO Products VALUES('orchid', 'orchid in a pot', '139', 'potted', 'white', null);

		 
	
	sprawdzenie ilosci: 
	 select count(*) from Products where name = 'rose'
	 select count(*) from Products where color = 'red'
	 select count(*) from products where type='bouquet'
	 
	 * */

	
	
	
	
	@Autowired
	ProductService productService;
	
	@Test
	public void findByNameCheck() {
		String name = "rose";
		int n = productService.findProductByName(name).size();
		
		assertEquals(3, n);
	}
	
	@Test
	public void findByColorCheck(){
		String color = "red";
		int n = productService.findProductByColor(color).size();
		
		assertEquals(4, n);
	}

	@Test
	public void findByTypeCheck(){
		String type = "bouquet";
		int n = productService.findProductByType(type).size();
		
		assertEquals(3, n);
	}
}
