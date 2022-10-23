package com.softit.omar.softit.servlets;

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
        PrintWriter writer= response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=root");
            writer.write("connected successfully!");
            PreparedStatement st=connection.prepareStatement("SELECT * FROM users;");
            st.execute();
            request.setAttribute("users",st.getResultSet());
            getServletContext().getRequestDispatcher("/WEB-INF/users_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
            writer.write("error de connection à la base de données!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //compte épargne/normal
        //user role
        //getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(request,response);
    }

    public void destroy() {
    }
}