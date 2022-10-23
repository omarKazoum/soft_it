package com.omar.softit.servlets;

import com.omar.softit.beans.User;
import com.omar.softit.dao.UserDAO;
import com.omar.softit.daoImplementations.UserHibernateDAO;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session==null)
        getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request,response);
        else{
            response.sendRedirect("users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO= UserHibernateDAO.getInstance();
        userDAO.setContext(getServletContext());
        userDAO.prepare();
        User user=userDAO.getUserByEmail(req.getParameter("email"));

        resp.getWriter().write("request sent"+ user.getFullName());
    }

}
