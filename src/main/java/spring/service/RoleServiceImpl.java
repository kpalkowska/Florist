package spring.service;

import spring.dao.RoleDAO;
import spring.model.RoleModel;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;
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
	
	@Override
	public List<RoleModel> getAllRoles() {
		return sessionFactory.getCurrentSession().getNamedQuery("roles.all").list();
	}
	@Override
	public void updateRole(RoleModel role) {
		sessionFactory.getCurrentSession().merge(role);
	}
	
	@Override
	public void addRole(RoleModel role) {
		sessionFactory.getCurrentSession().persist(role);
	}

	@Override
	public RoleModel getRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
