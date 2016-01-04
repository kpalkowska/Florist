package java.spring.service;

import java.spring.dao.RoleDAO;
import java.util.List;

import javax.management.relation.Role;

import org.hibernate.SessionFactory;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
   
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
   	private SessionFactory sessionFactory;

   	public SessionFactory getSessionFactory() {
   		return sessionFactory;
   	}

   	public void setSessionFactory(SessionFactory sessionFactory) {
   		this.sessionFactory = sessionFactory;
   	}

    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }

    
    @Override
	public void deleteRole(Role role) {
		sessionFactory.getCurrentSession().delete(role);	
	}
	
	@Override
	public List<Role> getAllRoles() {
		return sessionFactory.getCurrentSession().getNamedQuery("roles.all").list();
	}
	@Override
	public void updateRole(Role role) {
		sessionFactory.getCurrentSession().merge(role);
	}
	
	@Override
	public void addRole(Role role) {
		sessionFactory.getCurrentSession().persist(role);
	}

}
