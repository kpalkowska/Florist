package spring.service;

import spring.model.OrderModel;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderServiceImpl implements OrderService{

    @Autowired
	private SessionFactory sessionFactory;

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

}
