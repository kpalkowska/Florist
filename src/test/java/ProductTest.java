
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import spring.model.ProductModel;
import spring.service.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ProductTest{

	@Autowired
	Manager manager;

	private final String NAME_1 = "nowy";
	private final String DESCRIPTION_1 = "nowy";
	private final String PRICE_1 = "13,56";

	private final String NAME_2 = "nowszy";
	private final String DESCRIPTION_2 = "nowszy";
	private final String PRICE_2 = "20,56";
		
	boolean delete;
	
	private SessionFactory sessionFactory;
	private Session session = null;
	
	private List<Long>  addedProducts = new ArrayList<Long>();
	
	@Test
	public void addUserCheck() {

		int n = manager.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		manager.addProduct(product);
		ProductModel retrievedProduct = manager.findProductById(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, manager.getAllProducts().size());
	}
	
	@Test
	public void deleteUserCheck(){
	
		int n = manager.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		manager.addProduct(product);
		ProductModel retrievedProduct = manager.findProductById(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, manager.getAllProducts().size());
		
		manager.deleteProduct(product);
		assertEquals(n, manager.getAllProducts().size());
	}
	
	@Test 
	public void updateClientCheck(){
		int n = manager.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		manager.addProduct(product);
		ProductModel retrievedProduct = manager.findProductById(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, manager.getAllProducts().size());
		
		
		retrievedProduct.setName(NAME_2);
		retrievedProduct.setDescription(DESCRIPTION_2);
		retrievedProduct.setPrice(PRICE_2);

		manager.updateProduct(retrievedProduct);
		
		ProductModel retrievedProduct2 = manager.findProductById(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_2, retrievedProduct.getName());
		assertEquals(DESCRIPTION_2, retrievedProduct.getDescription());
		assertEquals(PRICE_2, retrievedProduct.getPrice());
		
	}
	
}