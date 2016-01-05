package spring.dao;

import spring.model.RoleModel;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {
   
	@Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addRole(RoleModel role) {
        getSessionFactory().getCurrentSession().save(role);
    }

    public void deleteRole(RoleModel role) {
        getSessionFactory().getCurrentSession().delete(role);
    }

    public void updateRole(RoleModel role) {
        getSessionFactory().getCurrentSession().update(role);
    }

    public RoleModel getRoleByName(String name) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Roles where name=?").setParameter(0, name).list();
        return (RoleModel)list.get(0);
    }

    public List<RoleModel> getAllRoles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Roles").list();
        return list;
    }

	@Override
	public RoleModel findRoleByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleModel getRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}