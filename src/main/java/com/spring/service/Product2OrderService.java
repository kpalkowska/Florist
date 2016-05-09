package com.spring.service;

import java.util.List;

import com.spring.model.OrderModel;
import com.spring.model.Product2OrderModel;
import com.spring.model.ProductModel;

public interface Product2OrderService {

	List<Product2OrderModel> getAllProducts2Orders();

	void addProduct2Order(Product2OrderModel product2order);

	Product2OrderModel findProduct2Order(Product2OrderModel product2order);

	void deleteProduct2Order(Product2OrderModel product2order);

	void updateProduct2Order(Product2OrderModel product2order);

	boolean createProduct2Order(ProductModel productModel, OrderModel orderModel);
}
