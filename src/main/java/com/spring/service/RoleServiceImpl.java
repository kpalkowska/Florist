package com.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.RoleModel;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
   	private SessionFactory sessionFactory;

   	public SessionFactory getSessionFactory() {
   		return sessionFactory;
   	}

   	public void setSessionFactory(SessionFactory sessionFactory) {
   		this.sessionFactory = sessionFactory;
   	}
    
    @Override
	public void deleteRole(RoleModel role) {
		sessionFactory.getCurrentSession().delete(role);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RoleModel> getAllRoles() {
		return sessionFactory.getCurrentSession().getNamedQuery("roles.all").list();
	}
	@Override
	@Transactional
	public void updateRole(RoleModel role) {
		sessionFactory.getCurrentSession().merge(role);
	}
	
	@Override
	@Transactional
	public void addRole(RoleModel role) {
		sessionFactory.getCurrentSession().persist(role);
	}

	@Override
	@Transactional
	public RoleModel findRole(RoleModel role) {
		return (RoleModel) sessionFactory.getCurrentSession().get(RoleModel.class, role.getId());
	}

	@Override
	@Transactional
	public RoleModel exists(String role) {
		return (RoleModel) sessionFactory.getCurrentSession().getNamedQuery("role.exists").setString("role", role).uniqueResult();
	}

}
