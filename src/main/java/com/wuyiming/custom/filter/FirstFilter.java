package com.wuyiming.custom.filter;

import com.wuyiming.custom.CustomFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
@Order(3)
public class FirstFilter implements CustomFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, CustomFilterChain chain) {
        System.out.println("start FirstFilter doFilter()");
        chain.doFilter(request, response);
        System.out.println("after FirstFilter doFilter()");
    }
}
