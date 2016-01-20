package spring.service;

import spring.model.UserModel;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void deleteUser(UserModel user) {
		sessionFactory.getCurrentSession().delete(user);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserModel> getAllUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("users.all").list();
	}
	@Override
	@Transactional
	public void updateUser(UserModel user) {
		sessionFactory.getCurrentSession().merge(user);
	}
	
	@Override
	@Transactional
	public void addUser(UserModel user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	@Transactional
	public UserModel findUser(UserModel user) {
		return (UserModel) sessionFactory.getCurrentSession().get(UserModel.class, user.getId());
	}
}