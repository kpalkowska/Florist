package com.spring.service;

import java.util.List;

import com.spring.model.AddressModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;

public interface UserService {
	 
	void addUser(UserModel user);
	List<UserModel> getAllUsers();
	void deleteUser(UserModel user);
	void updateUser(UserModel user);
	UserModel findUser(UserModel user);
	boolean createUser(String name, String surname, String login, String password, AddressModel address, RoleModel role);
	List<UserModel> getUsers();
	boolean createAddress(String zipCode, String city, String street, String number);
	boolean createRole(String name);
	UserModel findUserByLogin(String login);
}