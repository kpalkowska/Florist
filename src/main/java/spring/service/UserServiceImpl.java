package spring.service;

import spring.dao.UserDAO;
import spring.model.UserModel;

import java.util.List;

import org.hibernate.SessionFactory;
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
	public void deleteUser(UserModel user) {
		sessionFactory.getCurrentSession().delete(user);	
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("users.all").list();
	}
	@Override
	public void updateUser(UserModel user) {
		sessionFactory.getCurrentSession().merge(user);
	}
	
	@Override
	public void addUser(UserModel user) {
		sessionFactory.getCurrentSession().persist(user);
	}
}