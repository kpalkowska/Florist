package com.spring.service;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.dao.AddressDAO;
import com.spring.dao.RoleDAO;
import com.spring.dao.UserDAO;
import com.spring.model.AddressModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;
import com.spring.security.AppUser;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserDAO userDAO;

	@Override
	public void deleteUser(UserModel user) {
		userDAO.deleteUser(user);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public void updateUser(UserModel user) {
		userDAO.updateUser(user);
	}

	@Override
	public void addUser(UserModel user) {
		userDAO.addUser(user);
	}

	@Override
	public UserModel findUser(UserModel user) {
		return userDAO.findUser(user);
	}

	@Override
	public boolean createUser(String userName, String surname, String login, String password, AddressModel address,
			RoleModel role) {
		if (StringUtils.isEmpty(surname) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(login)
				|| StringUtils.isEmpty(password) || StringUtils.isEmpty(address) || StringUtils.isEmpty(role)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		} else if (userDAO.exists(login)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User already exists"));
			LOG.info(new StringBuilder("User ").append(userName).append(" already exists").toString());
		} else {
			UserModel user = new UserModel(userName, surname, login, passwordEncoder.encode(password), address, role);
			userDAO.addUser(user);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean createAddress(String zipCode, String city, String street, String number) {
		if (StringUtils.isEmpty(zipCode) || StringUtils.isEmpty(street) || StringUtils.isEmpty(city)
				|| StringUtils.isEmpty(number)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid address"));
			LOG.info(new StringBuilder("Address ").append(" cannot be empty").toString());
		} else if (addressDAO.exists(zipCode, city, street, number)) {
			return false;
		} else {
			AddressModel address = new AddressModel(zipCode, city, street, number);
			addressDAO.addAddress(address);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean createRole(String name) {
		if (StringUtils.isEmpty(name)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid role"));
			LOG.info(new StringBuilder("Role ").append(" cannot be empty").toString());
		} else if (!roleDAO.exists(name)) {
			RoleModel role = new RoleModel(name);
			roleDAO.addRole(role);
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

	@Override
	public UserModel findUserByLogin(String login) {
		return userDAO.findUserByName(login);
	}

}