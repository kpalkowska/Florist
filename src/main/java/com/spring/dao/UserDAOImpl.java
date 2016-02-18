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
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
		
		@Autowired
		public UserDAOImpl(SessionFactory sessionFactory) {
			super.setSessionFactory(sessionFactory);
		}

	    public void addUser(UserModel user) {
	        getHibernateTemplate().save(user);
	    }

	    public void deleteUser(UserModel user) {
	    	getHibernateTemplate().delete(user);
	    }

	    public void updateUser(UserModel user) {
	    	getHibernateTemplate().update(user);
	    }

		public List<UserModel> getAllUsers() {
			return getHibernateTemplate().loadAll(UserModel.class);
		}

		@Override
		public boolean exists(String name) {
			final String SQL = "select count(*) from UserModel user where user.name = :name";
			return getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
				@Override
				public Boolean doInHibernate(Session session) throws HibernateException {
					Long count = (Long) session.createQuery(SQL).setParameter("name", name).uniqueResult();
					return count > 0;
				}
			});
		}
		
		@Override
		public UserModel findUserByName(String username) {
			return getHibernateTemplate().execute(new HibernateCallback<UserModel>() {
					@Override
					public UserModel doInHibernate(Session session) throws HibernateException {
						Criteria criteria = session.createCriteria(UserModel.class);
		                criteria.add(Restrictions.eq("name", username));
						return (UserModel) criteria.uniqueResult();
					}
			});
		}
}
