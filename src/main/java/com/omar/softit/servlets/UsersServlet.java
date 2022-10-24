package com.omar.softit.servlets;

import com.omar.softit.entities.User;
import com.omar.softit.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
@WebServlet( urlPatterns = {"/users"},name = "usersServlet")
public class UsersServlet extends HttpServlet {
    private String message;
    UserService userService;
    public void init() {
        try {
            userService=new UserService(getServletContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action=request.getParameter("action");
        action=action==null?"":action;
        System.out.println("url:");
        if(action.equals("add")){
            getServletContext().getRequestDispatcher("/WEB-INF/users/add_form.jsp").forward(request,response);
        }else
        if(action.equals("delete")){
            long userId= Long.parseLong(request.getParameter("user_id"));
            userService.deleteUser(userId);
            response.sendRedirect(request.getRequestURL().toString());

        }else if(action.equals("edit")){
            long userId= Long.parseLong(request.getParameter("user_id"));
            User u=userService.getUser(userId);
            request.setAttribute("user_to_edit",u);
            getServletContext().getRequestDispatcher("/WEB-INF/users/edit_form.jsp").forward(request,response);
            System.out.println("user deleted!");

        }else {
            //if no action is specified then the user wil see a list of all available users
            request.setAttribute("users",userService.getAll());
            getServletContext().getRequestDispatcher("/WEB-INF/users/users_list.jsp").forward(request,response);
        }

       }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        action=action==null?"":action;
        if(action.equals("add")){
            User u=new User();
            u.setEmail(request.getParameter("email"));
            u.setFullName(request.getParameter("full_name"));
            u.setPassword(request.getParameter("password"));
            userService.addUser(u);
            response.sendRedirect(request.getRequestURL().toString());
        }else if(action.equals("edit")){
            User u=userService.getUser(Long.parseLong(request.getParameter("id")));
            u.setEmail(request.getParameter("email"));
            u.setFullName(request.getParameter("full_name"));
            u.setPassword(request.getParameter("password"));
            userService.userUpdate(u);
            response.sendRedirect(request.getRequestURL().toString());
        }

       }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        resp.getWriter().write("success");
    }

    public void destroy() {
    }
}