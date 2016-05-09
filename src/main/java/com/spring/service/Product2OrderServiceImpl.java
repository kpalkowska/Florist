package com.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.dao.Product2OrderDAO;
import com.spring.model.OrderModel;
import com.spring.model.Product2OrderModel;
import com.spring.model.ProductModel;

@Service(value = "product2orderService")
public class Product2OrderServiceImpl implements Product2OrderService {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private Product2OrderDAO product2OrderDAO;

	@Override
	public void deleteProduct2Order(Product2OrderModel product2order) {
		product2OrderDAO.deleteProduct2Order(product2order);
	}

	@Override
	public List<Product2OrderModel> getAllProducts2Orders() {
		return product2OrderDAO.getAllProduct2Orders();
	}

	@Override
	public void updateProduct2Order(Product2OrderModel product2order) {
		product2OrderDAO.updateProduct2Order(product2order);
	}

	@Override
	public void addProduct2Order(Product2OrderModel product2order) {
		product2OrderDAO.addProduct2Order(product2order);
	}

	@Override
	public Product2OrderModel findProduct2Order(Product2OrderModel product2order) {
		return product2OrderDAO.findProduct2Order(product2order);
	}

	@Override
	public boolean createProduct2Order(ProductModel product, OrderModel order) {
		if (StringUtils.isEmpty(product) || StringUtils.isEmpty(order)) {
			LOGGER.error("Error in create Product2Order");
		} else {
			Product2OrderModel p2o = new Product2OrderModel(order, product);
			product2OrderDAO.addProduct2Order(p2o);
			return true;
		}
		return false;
	}

}
