package java.spring.service;

import java.util.List;

import org.hsqldb.rights.User;

public interface UserService {
	 
	void addUser(User user);
	List<User> getAllUsers();
	void deleteUser(User user);
	void updateUser(User user);

}