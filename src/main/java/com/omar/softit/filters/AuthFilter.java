package com.omar.softit.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "authFilter",urlPatterns = {"/login"},servletNames = {"loginServlet"},description = "redirect authenticated users to their home page")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=((HttpServletRequest)request).getSession(false);
        if(!(session!=null && session.getAttribute("user_mail")!=null) || ((HttpServletRequest) request).getRequestURI().endsWith("/logout")){
            chain.doFilter(request,response);
        }else ((HttpServletResponse)response).sendRedirect("users");
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
