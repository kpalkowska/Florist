package spring.service;

import spring.dao.OrderDAO;

import spring.model.OrderModel;
import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl {


    private OrderDAO orderDAO;
    @Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void deleteOrder(OrderModel order) {
		sessionFactory.getCurrentSession().delete(order);	
	}
	

	public List<OrderModel> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("orders.all").list();
	}

	public void updateOrder(OrderModel order) {
		sessionFactory.getCurrentSession().merge(order);
	}

	public void addOrder(OrderModel order) {
		sessionFactory.getCurrentSession().persist(order);
	}

}
