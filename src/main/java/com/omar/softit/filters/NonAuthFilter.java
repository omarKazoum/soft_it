package com.omar.softit.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(filterName = "nonAuthFilter",urlPatterns = {"/users"},servletNames = {"usersServlet"},description = "filter non authenticated users")
public class NonAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=((HttpServletRequest)request).getSession(false);
        if(session==null || session.getAttribute("user_mail")==null){
            ((HttpServletResponse)response).sendRedirect("login");
        }else
            chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
