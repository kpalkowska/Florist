package java.spring.service;

import java.spring.dao.UserDAO;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
   
    @Autowired
    private UserDAO userDAO;

    @Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);	
	}
	
	@Override
	public List<User> getAllUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("users.all").list();
	}
	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}
	
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}


}