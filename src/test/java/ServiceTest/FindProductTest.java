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
	
	
	INSERT INTO Products VALUES('róża', 'czerwnoy 5 cm', '2.99', 'bukiet', 'żółty', null);
	INSERT INTO Products VALUES('tulipan', 'żółty', '1.99', 'jeden', 'czerwony', null);
	INSERT INTO Products VALUES('margaretka', 'pomarańczowy', '3', 'wiązanka', 'niebieski', null);
	INSERT INTO Products VALUES('róża', 'czerwony', '2.99', 'bukiet', 'żółty', null);
	INSERT INTO Products VALUES('tulipan', 'żółty', '1.99', 'jeden', 'czerwony', null);
	INSERT INTO Products VALUES('margaretka', 'pomarańczowy', '3', 'wiązanka', 'niebieski', null);
	INSERT INTO Products VALUES('róża', 'czerwony', '2.99', 'bukiet', 'żółty', null);
	INSERT INTO Products VALUES('tulipan', 'żółty', '1.99', 'jeden', 'czerwony', null);
	INSERT INTO Products VALUES('margaretka', 'pomarańczowy', '3', 'bukiet', 'niebieski', null);
		 
	
	 prawdzenie ilosci: 
	 select count(*) from Products where name = 'róża'
	 select count(*) from Products where color = 'czerwony'
	 select count(*) from products where type='bukiet'
	 
	 * */

	
	
	
	
	@Autowired
	ProductService productService;
	
	@Test
	public void findByNameCheck() {
		String name = "róża";
		int n = productService.findProductByName(name).size();
		
		assertEquals(3, n);
	}
	
	@Test
	public void findByColorCheck(){
		String color = "czerwony";
		int n = productService.findProductByColor(color).size();
		
		assertEquals(3, n);
	}

	@Test
	public void findByTypeCheck(){
		String type = "bukiet";
		int n = productService.findProductByType(type).size();
		
		assertEquals(4, n);
	}
}
