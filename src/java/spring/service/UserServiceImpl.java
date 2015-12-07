package java.spring.service;

import java.spring.dao.UserDAO;

import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
   
    @Autowired
    private UserDAO userDAO;

    public User getUser(int id) {
        return userDAO.getUserById(id);
    }

}