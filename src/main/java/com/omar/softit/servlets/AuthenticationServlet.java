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
import java.util.Optional;

@WebServlet(urlPatterns = {"/login"})
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("email")!=null)
            response.sendRedirect("users");
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO= UserHibernateDAO.getInstance();
        userDAO.setContext(getServletContext());
        userDAO.prepare();
        String email=req.getParameter("email");
        Optional<User> user=userDAO.getUserByEmail(email).stream().findFirst();
        if(user.isPresent() && user.get().getPassword().equals(req.getParameter("password"))){
            resp.getWriter().write("user found");
            HttpSession session=req.getSession(true);
            session.setMaxInactiveInterval(-1);
            session.setAttribute("user_mail",email);
            resp.sendRedirect("users");
        }else
        {
            resp.getWriter().write("user not found");
        }
    }

}
