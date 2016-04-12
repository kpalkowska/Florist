package com.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.*;

@Repository
@Transactional
public class Product2OrderDAOImpl extends HibernateDaoSupport implements Product2OrderDAO {

	@Autowired
	public Product2OrderDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void addProduct2Order(Product2OrderModel product2Order) {
		getHibernateTemplate().save(product2Order);
	}

	@Override
	public void deleteProduct2Order(Product2OrderModel product2Order) {
		getHibernateTemplate().delete(product2Order);
	}

	@Override
	public void updateProduct2Order(Product2OrderModel product2Order) {
		getHibernateTemplate().update(product2Order);
	}

	@Override
	public List<Product2OrderModel> getAllProduct2Orders() {
		return getHibernateTemplate().loadAll(Product2OrderModel.class);
	}
	
	@Override
	public Product2OrderModel findProduct2Order(Product2OrderModel product2order) {
		return (Product2OrderModel) getHibernateTemplate().get(Product2OrderModel.class,
				product2order.getId());
	}

}
