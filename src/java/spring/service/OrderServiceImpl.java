package java.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hsqldb.rights.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void deleteOrder(Order order) {
		sessionFactory.getCurrentSession().delete(order);	
	}
	
	@Override
	public List<Order> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("orders.all").list();
	}
	@Override
	public void updateOrder(Order order) {
		sessionFactory.getCurrentSession().merge(order);
	}
	
	@Override
	public void addOrder(Order order) {
		sessionFactory.getCurrentSession().persist(order);
	}

}
