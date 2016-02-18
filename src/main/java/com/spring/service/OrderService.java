package com.spring.service;

import java.util.List;

import com.spring.model.OrderModel;

public interface OrderService {
	
	void addOrder(OrderModel order);
	List<OrderModel> getAllOrders();
	void deleteOrder(OrderModel order);
	void updateOrder(OrderModel order);
	OrderModel findOrder(OrderModel order);
}
