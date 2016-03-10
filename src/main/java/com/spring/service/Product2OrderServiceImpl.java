package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.dao.Product2OrderDAO;
import com.spring.model.OrderModel;
import com.spring.model.Product2OrderModel;
import com.spring.model.ProductModel;

@Component
public class Product2OrderServiceImpl implements Product2OrderService{
	
	@Autowired
	private Product2OrderDAO product2OrderDAO;
	
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

	@Override
	public boolean createProduct2Order(ProductModel product, OrderModel order) {
		if (StringUtils.isEmpty(product) || StringUtils.isEmpty(order)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		}  else {
			Product2OrderModel p2o = new Product2OrderModel(order, product);
			product2OrderDAO.addProduct2Order(p2o);
			return true;
		}
		return false;
	}

}
