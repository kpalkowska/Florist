package ServiceTest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;

import spring.model.ProductModel;
import spring.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml" })
@Transactional("txManager")
public class ProductServiceTest{

	@Autowired
	ProductService productService;

	private final String NAME_1 = "nowy";
	private final String DESCRIPTION_1 = "nowy";
	private final String PRICE_1 = "13,56";

	private final String NAME_2 = "nowszy";
	private final String DESCRIPTION_2 = "nowszy";
	private final String PRICE_2 = "20,56";
		
	@Rollback(false)
	@Test
	public void addProductCheck() {

		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		productService.addProduct(product);

		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, productService.getAllProducts().size());
	}
	
	@Rollback(false)
	@Test
	public void deleteProductCheck(){
	
		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		productService.addProduct(product);
	
		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, productService.getAllProducts().size());
		
		productService.deleteProduct(product);
		assertEquals(n, productService.getAllProducts().size());
	}
	
	@Rollback(false)
	@Test 
	public void updateProductCheck(){
		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);

		productService.addProduct(product);
	
		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		
		assertEquals(n+1, productService.getAllProducts().size());
		
		
		retrievedProduct.setName(NAME_2);
		retrievedProduct.setDescription(DESCRIPTION_2);
		retrievedProduct.setPrice(PRICE_2);

		productService.updateProduct(retrievedProduct);
	
		ProductModel retrievedProduct2 = productService.findProduct(product);
		assertEquals(retrievedProduct.getId(), retrievedProduct2.getId());
		assertEquals(NAME_2, retrievedProduct2.getName());
		assertEquals(DESCRIPTION_2, retrievedProduct2.getDescription());
		assertEquals(PRICE_2, retrievedProduct2.getPrice());
		
	}
	
}