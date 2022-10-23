package com.omar.softit.daoImplementations;

import com.omar.softit.beans.User;
import com.omar.softit.dao.UserDAO;
import jakarta.servlet.ServletContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

public class UserHibernateDAO implements UserDAO {
    private ServletContext servletContext;
    private static UserHibernateDAO instance;
    SessionFactory sessionFactory;
    public static UserHibernateDAO getInstance(){
        if(UserHibernateDAO.instance==null){
            instance=new UserHibernateDAO();
        }
        return instance;
    }

    private UserHibernateDAO() {
    }

    @Override
    public void prepare() throws FileNotFoundException, MalformedURLException {
        StandardServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure(servletContext.getResource("WEB-INF/hibernate.cnf.xml")).build();
        sessionFactory=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    @Override
    public User getUser(long id) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        User user=session.get(User.class,id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void delete(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
         session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void save(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
         session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAll() {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<User> users=(List<User>)session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void setContext(ServletContext context) {
        servletContext=context;
    }
}
