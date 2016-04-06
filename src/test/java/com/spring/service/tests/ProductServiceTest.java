package com.spring.service.tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;

import com.spring.model.ProductModel;
import com.spring.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
@Transactional("txManager")
public class ProductServiceTest{

	@Autowired
	ProductService productService;

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
	
		
	@Rollback(true)
	@Test
	public void addAndFindProductCheck() {

		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);

		productService.addProduct(product);

		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		assertEquals(TYPE_1, retrievedProduct.getType());
		assertEquals(COLOR_1, retrievedProduct.getColor());
		
		int m = productService.findProductByName(NAME_1).size();
		
		assertEquals(1, m);
		assertEquals(n+1, productService.getAllProducts().size());
	}
	
	@Rollback(true)
	@Test
	public void deleteAndFindProductCheck(){
	
		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);
		productService.addProduct(product);
	
		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		assertEquals(TYPE_1, retrievedProduct.getType());
		assertEquals(COLOR_1, retrievedProduct.getColor());
		
		assertEquals(n+1, productService.getAllProducts().size());
		
		productService.deleteProduct(product);
		
		int m = productService.findProductByName(NAME_1).size();
		
		assertEquals(0, m);
		assertEquals(n, productService.getAllProducts().size());
	}
	
	@Rollback(true)
	@Test 
	public void updateProductCheck(){
		int n = productService.getAllProducts().size();
		ProductModel product = new ProductModel(NAME_1, DESCRIPTION_1, PRICE_1, TYPE_1, COLOR_1);
		product.setName(NAME_1);
		product.setDescription(DESCRIPTION_1);
		product.setPrice(PRICE_1);
		product.setType(TYPE_1);
		product.setColor(COLOR_1);

		productService.addProduct(product);
	
		ProductModel retrievedProduct = productService.findProduct(product);
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(NAME_1, retrievedProduct.getName());
		assertEquals(DESCRIPTION_1, retrievedProduct.getDescription());
		assertEquals(PRICE_1, retrievedProduct.getPrice());
		assertEquals(TYPE_1, retrievedProduct.getType());
		assertEquals(COLOR_1, retrievedProduct.getColor());
		
		assertEquals(n+1, productService.getAllProducts().size());
		
		
		retrievedProduct.setName(NAME_2);
		retrievedProduct.setDescription(DESCRIPTION_2);
		retrievedProduct.setPrice(PRICE_2);
		retrievedProduct.setType(TYPE_2);
		retrievedProduct.setColor(COLOR_2);
			
		productService.updateProduct(retrievedProduct);
	
		ProductModel retrievedProduct2 = productService.findProduct(product);
		assertEquals(retrievedProduct.getId(), retrievedProduct2.getId());
		assertEquals(NAME_2, retrievedProduct2.getName());
		assertEquals(DESCRIPTION_2, retrievedProduct2.getDescription());
		assertEquals(PRICE_2, retrievedProduct2.getPrice());
		assertEquals(TYPE_2, retrievedProduct2.getType());
		assertEquals(COLOR_2, retrievedProduct2.getColor());
		
	}
	
}