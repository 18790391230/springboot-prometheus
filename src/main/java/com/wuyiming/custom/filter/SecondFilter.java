package com.wuyiming.custom.filter;

import com.wuyiming.custom.CustomFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
@Order(2)
public class SecondFilter implements CustomFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain) {
        System.out.println("start SecondFilter doFilter()");
        chain.doFilter(request, response);
        System.out.println("after SecondFilter doFilter()");
    }
}
