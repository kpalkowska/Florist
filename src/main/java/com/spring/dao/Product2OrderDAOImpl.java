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

	    public void addProduct2Order(Product2OrderModel product2Order) {
	    	getHibernateTemplate().save(product2Order);
	    }

	    public void deleteProduct2Order(Product2OrderModel product2Order) {
	    	getHibernateTemplate().delete(product2Order);
	    }

	    public void updateProduct2Order(Product2OrderModel product2Order) {
	    	getHibernateTemplate().update(product2Order);
	    }

		public List<Product2OrderModel> getAllProduct2Orders() {
			return getHibernateTemplate().loadAll(Product2OrderModel.class);
		}

}
