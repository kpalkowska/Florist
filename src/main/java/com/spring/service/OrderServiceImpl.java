package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.dao.OrderDAO;
import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.ProductModel;
import com.spring.model.UserModel;

@Component
public class OrderServiceImpl implements OrderService{

    @Autowired
	private SessionFactory sessionFactory;
    
    @Autowired
    private OrderDAO orderDAO;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void deleteOrder(OrderModel order) {
		sessionFactory.getCurrentSession().delete(order);	
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<OrderModel> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("orders.all").list();
	}

	@Override
	@Transactional
	public void updateOrder(OrderModel order) {
		sessionFactory.getCurrentSession().merge(order);
	}

	@Override
	@Transactional
	public void addOrder(OrderModel order) {
		sessionFactory.getCurrentSession().persist(order);
	}
	
	@Override
	@Transactional
	public OrderModel findOrder(OrderModel order) {
		return (OrderModel) sessionFactory.getCurrentSession().get(OrderModel.class, order.getId());
	}

	@Override
	public boolean createOrder(String date, UserModel user, AddressModel address) {
		if (StringUtils.isEmpty(date) || StringUtils.isEmpty(user) || StringUtils.isEmpty(address)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		}  else {
			OrderModel order = new OrderModel(date, user, address);
			orderDAO.addOrder(order);
			return true;
		}
		
		return false;
	}
	
	@Override
	@Transactional
	public OrderModel exists(String date, UserModel user, AddressModel address){
		return (OrderModel) sessionFactory.getCurrentSession().getNamedQuery("order.exists")
				.setString("date", date)
				.setParameter("users", user)
				.setParameter("address", address)
				.uniqueResult();
						
	}
		

}
