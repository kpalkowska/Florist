package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.dao.OrderDAO;
import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.UserModel;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<OrderModel> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	@Override
	public void updateOrder(OrderModel order) {
		orderDAO.updateOrder(order);
	}

	@Override
	public void addOrder(OrderModel order) {
		orderDAO.addOrder(order);
	}
	
	@Override
	public void deleteOrder(OrderModel order) {
		orderDAO.deleteOrder(order);
	}

	@Override
	public OrderModel findOrder(OrderModel order) {
		return orderDAO.findOrder(order);
	}

	@Override
	public OrderModel exists(AddressModel address, String date, UserModel user) {
		return orderDAO.exists(address, date, user);
	}
	
	@Override
	public boolean createOrder(AddressModel address, String date, UserModel user) {
		if (StringUtils.isEmpty(date) || StringUtils.isEmpty(user) || StringUtils.isEmpty(address)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		} else {
			OrderModel order = new OrderModel(date, user, address);
			orderDAO.addOrder(order);
			return true;
		}
		return false;
	}

}
