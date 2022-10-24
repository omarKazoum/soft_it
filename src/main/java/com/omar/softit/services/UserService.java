package com.omar.softit.services;

import com.omar.softit.entities.User;
import com.omar.softit.dao.UserDAO;
import com.omar.softit.daoImplementations.UserHibernateDAO;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.util.List;

public class UserService {
    UserDAO userDAO;
    public UserService(ServletContext context) throws IOException {
        this.userDAO = UserHibernateDAO.getInstance();
        userDAO.setContext(context);
        userDAO.prepare();
    }

    public static List<User> getAllUsers(){
        return null;
    }
    public void addUser(User user){
          userDAO.save(user);
    }
    public void deleteUser(long id){
        User u=userDAO.getUser(id);
        userDAO.delete(u);
    }
    public void userUpdate(User user){
        userDAO.update(user);
    }
    public User getUser(long userId){
        return  userDAO.getUser(userId);
    }


    public Object getAll() {
        return userDAO.getAll();
    }
}
