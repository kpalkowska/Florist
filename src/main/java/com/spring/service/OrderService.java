package com.spring.service;

import java.util.List;

import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.UserModel;

public interface OrderService {

	void addOrder(OrderModel order);

	List<OrderModel> getAllOrders();

	void deleteOrder(OrderModel order);

	void updateOrder(OrderModel order);

	OrderModel findOrder(OrderModel order);

	boolean createOrder(AddressModel address, String date, UserModel user);

	OrderModel exists(AddressModel address, String date, UserModel user);
}
