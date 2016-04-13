package com.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	@Override
	public void addOrder(OrderModel order) {
		getHibernateTemplate().save(order);
	}

	@Override
	public void deleteOrder(OrderModel order) {
		getHibernateTemplate().delete(order);
	}

	@Override
	public void updateOrder(OrderModel order) {
		getHibernateTemplate().update(order);
	}
	
	@Override
	public OrderModel exists(AddressModel address, String date, UserModel user) {
		return getHibernateTemplate().execute(new HibernateCallback<OrderModel>() {
			@Override
			public OrderModel doInHibernate(Session session) throws HibernateException {
				OrderModel order = (OrderModel) session.createQuery(OrderModel.ORDER_EXISTS).setString("date", date)
						.setParameter("users", user).setParameter("address", address).uniqueResult();
				return order;
			}
		});
	}

	@Override
	public List<OrderModel> getAllOrders() {
		return getHibernateTemplate().loadAll(OrderModel.class);
	}
	
	@Override
	public OrderModel findOrder(OrderModel order) {
		return (OrderModel) getHibernateTemplate().get(OrderModel.class, order.getId());
	}

}
