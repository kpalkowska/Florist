package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.*;

@Repository
@Transactional
public class OrderDAOImpl extends HibernateDaoSupport implements OrderDAO {
	
		@Autowired
		public OrderDAOImpl(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}

	    public void addOrder(OrderModel order) {
	    	getHibernateTemplate().save(order);
	    }

	    public void deleteOrder(OrderModel order) {
	    	getHibernateTemplate().delete(order);
	    }

	    public void updateOrder(OrderModel order) {
	    	getHibernateTemplate().update(order);
	    }

		@Override
		public OrderModel findOrderByName(String name) {
			return getHibernateTemplate().execute(new HibernateCallback<OrderModel>() {
				@Override
				public OrderModel doInHibernate(Session session) throws HibernateException {
					Criteria criteria = session.createCriteria(OrderModel.class);
	                criteria.add(Restrictions.eq("name", name));
					return (OrderModel) criteria.uniqueResult();
				}
		});
		}

		public List<OrderModel> getAllOrders() {
			return getHibernateTemplate().loadAll(OrderModel.class);
		}



}
