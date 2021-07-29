package com.wuyiming.custom.filter;

import com.wuyiming.custom.CustomFilterChain;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface CustomFilter {

    void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain);
}
