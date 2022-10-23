package com.omar.softit.utils;

import jakarta.servlet.ServletContext;

public interface NeedsContext {
    void setContext(ServletContext context);
}