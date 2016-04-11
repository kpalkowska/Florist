package com.spring.dao;

import java.util.List;

import com.spring.model.*;

public interface Product2OrderDAO {

	List<Product2OrderModel> getAllProduct2Orders();

	void addProduct2Order(Product2OrderModel product2Order);

	void deleteProduct2Order(Product2OrderModel product2Order);

	void updateProduct2Order(Product2OrderModel product2Order);
}
