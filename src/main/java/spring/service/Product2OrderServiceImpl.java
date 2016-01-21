package spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.model.Product2OrderModel;

@Component
public class Product2OrderServiceImpl implements Product2OrderService{
	
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
	public void deleteProduct2Order(Product2OrderModel product2order) {
		sessionFactory.getCurrentSession().delete(product2order);	
	}
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product2OrderModel> getAllProducts2Orders() {
		return sessionFactory.getCurrentSession().getNamedQuery("products2orders.all").list();
	}

	@Override
	@Transactional
	public void updateProduct2Order(Product2OrderModel product2order) {
		sessionFactory.getCurrentSession().merge(product2order);
	}

	@Override
	@Transactional
	public void addProduct2Order(Product2OrderModel product2order) {
		sessionFactory.getCurrentSession().persist(product2order);
	}
	
	@Override
	@Transactional
	public Product2OrderModel findProduct2Order(Product2OrderModel product2order) {
		return (Product2OrderModel) sessionFactory.getCurrentSession().get(Product2OrderModel.class, product2order.getId());
	}

}
