package com.spring.dao;

import java.util.List;

import com.spring.model.*;


public interface UserDAO {
	
	List<UserModel> getAllUsers();
	void addUser(UserModel user);
	UserModel findUserByName(String name);
	void deleteUser(UserModel user);
	void updateUser(UserModel user);
	boolean exists(String name);
}


