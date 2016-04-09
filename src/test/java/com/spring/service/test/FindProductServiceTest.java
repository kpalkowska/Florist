package com.spring.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context-test.xml" })
@Transactional("txManager")
public class FindProductServiceTest {

	@Autowired
	ProductService productService;

	@Test
	public void findByNameCheck() {
		String name = "rose";
		int n = productService.findProductByName(name).size();

		assertEquals(3, n);
	}

	@Test
	public void findByColorCheck() {
		String color = "red";
		int n = productService.findProductByColor(color).size();

		assertEquals(4, n);
	}

	@Test
	public void findByTypeCheck() {
		String type = "bouquet";
		int n = productService.findProductByType(type).size();

		assertEquals(3, n);
	}
}
