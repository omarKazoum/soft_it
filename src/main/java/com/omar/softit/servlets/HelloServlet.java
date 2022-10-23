package com.omar.softit.servlets;

import com.omar.softit.beans.User;
import com.omar.softit.dao.UserDAO;
import com.omar.softit.daoImplementations.UserHibernateDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

import static com.mysql.cj.conf.PropertyKey.logger;

@WebServlet( value = {"/users","/test"})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDAO userDAO=UserHibernateDAO.getInstance();
        userDAO.setContext(getServletContext());
        userDAO.prepare();
        PrintWriter writer= response.getWriter();
        /*User user= userDAO.getUser(1);
            writer.write(user==null?"null":user.toString());
*/
      /*  User u=new User();
        u.setEmail("another@gmail.Com");
        u.setFullName("omar kazoum");
        userDAO.save(u);*/
      /*  User u=userDAO.getUser(2);
        //u.setFullName("khalil elkadih");
        userDAO.delete(u);*/
        writer.write("<ul>");
        userDAO.getAll().stream().map(u->"<li>"+u.toString()+"</li>").forEach(writer::println);
        writer.write("</ul>");
    }

    public void destroy() {
    }
}