package com.spring.dao;

import java.util.List;

import com.spring.model.*;

public interface OrderDAO {

	List<OrderModel> getAllOrders();

	void addOrder(OrderModel order);

	void deleteOrder(OrderModel order);

	void updateOrder(OrderModel order);

	OrderModel findOrder(OrderModel order);

	OrderModel exists(AddressModel address, String date, UserModel user);
}