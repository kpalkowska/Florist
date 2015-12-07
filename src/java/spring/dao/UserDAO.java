package java.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	 
	    @Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addUser(User user) {
	        getSessionFactory().getCurrentSession().save(user);
	    }

	    public void deleteUser(User user) {
	        getSessionFactory().getCurrentSession().delete(user);
	    }

	    public void updateCustomer(User user) {
	        getSessionFactory().getCurrentSession().update(user);
	    }

	    public User getUserById(int id) {
	        List list = getSessionFactory().getCurrentSession().createQuery("from User where id=?").setParameter(0, id).list();
	        return (User)list.get(0);
	    }

	    public List<User> getUsers() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from User").list();
	        return list;
	    }
	 
	}
