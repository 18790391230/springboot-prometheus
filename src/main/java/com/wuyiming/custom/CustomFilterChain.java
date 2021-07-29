package com.wuyiming.custom;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface CustomFilterChain {

    void doFilter(ServletRequest request, ServletResponse response);
}
