package com.spring.service;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.dao.UserDAO;
import com.spring.model.AddressModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;
import com.spring.security.AppUser;

@Component
@Service(value="userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Value(value="${user.default.password}")
	private String defaultPassword;
	
	//@Value(value="${user.default.role}")
	//private String roleName;
	
	//private RoleModel defaultRole = new RoleModel(roleName);
	
	@Autowired
	private PasswordEncoder encoder;
	
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

	@Override
	public boolean createUser(String userName, String surname, String login, String defaultPassword, AddressModel address, RoleModel role) {
		if (StringUtils.isEmpty(userName)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
			LOG.info(new StringBuilder("User ").append(userName).append(" already exists").toString());
		} else if (userDAO.exists(userName)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User already exists"));
			LOG.info(new StringBuilder("User ").append(userName).append(" already exists").toString());
		} else {
			UserModel user = new UserModel(userName, surname, login, encoder.encode(defaultPassword), address, role);
			userDAO.addUser(user);
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserModel> getUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		UserModel user = userDAO.findUserByName(username);
		if (Objects.nonNull(user)) {
			return new AppUser(user);
		} else {
			throw new UsernameNotFoundException("Unable to load user");
		}
	}
}