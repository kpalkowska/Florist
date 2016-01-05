package java.spring.dao;

import javax.management.relation.Role;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {
   
    @Autowired
    private SessionFactory sessionFactory;
   
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
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

    public User getRoleByName(String name) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Roles where name=?").setParameter(0, name).list();
        return (RoleModel)list.get(0);
    }

    public List<RoleModel> getAllRoles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Roles").list();
        return list;
    }
    public RoleModel getRole(int id) {
        Role role = (RoleModel) getCurrentSession().load(RoleModel.class, id);
        return role;
    }
}