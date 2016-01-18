package spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
		
		@Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addUser(UserModel user) {
	        getSessionFactory().getCurrentSession().save(user);
	    }

	    public void deleteUser(UserModel user) {
	        getSessionFactory().getCurrentSession().delete(user);
	    }

	    public void updateUser(UserModel user) {
	        getSessionFactory().getCurrentSession().update(user);
	    }

	    public UserModel getUserByName(String name) {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Users where name=?").setParameter(0, name).list();
	        return (UserModel)list.get(0);
	    }

	    public List<UserModel> getAllUsers() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Users").list();
	        return list;
	    }

		@Override
		public UserModel findUserByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}

}
