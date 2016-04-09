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

import com.spring.model.RoleModel;

@Repository
@Transactional
public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

	@Autowired
	public RoleDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void addRole(RoleModel role) {
		getHibernateTemplate().save(role);
	}

	public void deleteRole(RoleModel role) {
		getHibernateTemplate().delete(role);
	}

	public void updateRole(RoleModel role) {
		getHibernateTemplate().update(role);
	}

	public List<RoleModel> getAllRoles() {
		return getHibernateTemplate().loadAll(RoleModel.class);
	}

	@Override
	public boolean exists(String role) {
		final String SQL = "select count(*) from RoleModel r where r.role = :role";
		return getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				Long count = (Long) session.createQuery(SQL).setParameter("role", role).uniqueResult();
				return count > 0;
			}
		});
	}
}