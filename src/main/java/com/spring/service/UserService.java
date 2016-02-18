package com.spring.service;

import java.util.List;

import com.spring.model.UserModel;

public interface UserService {
	 
	void addUser(UserModel user);
	List<UserModel> getAllUsers();
	void deleteUser(UserModel user);
	void updateUser(UserModel user);
	UserModel findUser(UserModel user);
}