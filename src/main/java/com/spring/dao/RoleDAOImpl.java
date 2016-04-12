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

	@Override
	public void addRole(RoleModel role) {
		getHibernateTemplate().save(role);
	}

	@Override
	public void deleteRole(RoleModel role) {
		getHibernateTemplate().delete(role);
	}

	@Override
	public void updateRole(RoleModel role) {
		getHibernateTemplate().update(role);
	}

	@Override
	public List<RoleModel> getAllRoles() {
		return getHibernateTemplate().loadAll(RoleModel.class);
	}
	
	@Override
	public RoleModel findRole(RoleModel role) {
		return (RoleModel) getHibernateTemplate().get(RoleModel.class, role.getId());
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
	
	@Override
	public RoleModel existed(String role) {
		final String SQL = "select count(*) from RoleModel r where r.role = :role";
		return getHibernateTemplate().execute(new HibernateCallback<RoleModel>() {
			@Override
			public RoleModel doInHibernate(Session session) throws HibernateException {
				RoleModel roleM = (RoleModel) session.createQuery(SQL).setParameter("role", role).uniqueResult();
				return roleM;
			}
		});
	}
}