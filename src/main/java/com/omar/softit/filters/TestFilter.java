package com.omar.softit.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@WebFilter(urlPatterns = {"/*"})
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        String appBaseUrl="http://"+req.getLocalName()+":"+req.getLocalPort()+req.getContextPath();
        System.out.println("test filter: "+appBaseUrl);
        req.setAttribute("app_base_url",appBaseUrl);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
