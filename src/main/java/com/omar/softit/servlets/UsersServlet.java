package com.omar.softit.servlets;

import com.omar.softit.beans.User;
import com.omar.softit.dao.UserDAO;
import com.omar.softit.daoImplementations.UserHibernateDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

@WebServlet( urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession(false);
        if(session==null || session.getAttribute("user_mail")==null){
            response.sendRedirect("login");
            return ;
        }
        UserDAO userDAO=UserHibernateDAO.getInstance();
        userDAO.setContext(getServletContext());
        userDAO.prepare();
        Writer writer=response.getWriter();
        String action=request.getParameter("action");
        action=action==null?"":action;
        System.out.println("url:");
        if(action.equals("add")){
            getServletContext().getRequestDispatcher("/WEB-INF/users/add_form.jsp").forward(request,response);
        }else
        if(action.equals("delete")){
            long userId= Long.parseLong(request.getParameter("user_id"));
            User u=userDAO.getUser(userId);
            userDAO.delete(u);
            System.out.println("user deleted!");
            response.sendRedirect(request.getRequestURL().toString());

        }else if(action.equals("edit")){
            long userId= Long.parseLong(request.getParameter("user_id"));
            User u=userDAO.getUser(userId);
            request.setAttribute("user_to_edit",u);
            getServletContext().getRequestDispatcher("/WEB-INF/users/edit_form.jsp").forward(request,response);
            System.out.println("user deleted!");

        }else {
            //if no action is specified then the user wil see a list of all available users
            request.setAttribute("users",userDAO.getAll());
            getServletContext().getRequestDispatcher("/WEB-INF/users/users_list.jsp").forward(request,response);

        }

       }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO=UserHibernateDAO.getInstance();
        userDAO.setContext(getServletContext());
        Writer writer=response.getWriter();
        userDAO.prepare();

        String action=request.getParameter("action");
        action=action==null?"":action;
        if(action.equals("add")){
            User u=new User();
            u.setEmail(request.getParameter("email"));
            u.setFullName(request.getParameter("full_name"));
            u.setPassword(request.getParameter("password"));
            userDAO.save(u);
            response.sendRedirect(request.getRequestURL().toString());
        }else if(action.equals("edit")){
            User u=userDAO.getUser(Long.parseLong(request.getParameter("id")));
            u.setEmail(request.getParameter("email"));
            u.setFullName(request.getParameter("full_name"));
            u.setPassword(request.getParameter("password"));
            userDAO.update(u);
            response.sendRedirect(request.getRequestURL().toString());
        }

       }


    public void destroy() {
    }
}