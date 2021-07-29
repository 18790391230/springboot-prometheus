package com.wuyiming.custom;

import com.wuyiming.custom.filter.CustomFilter;
import com.wuyiming.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

@Service
@Scope(value = "prototype")
public class ApplicationCustomFilterChain implements CustomFilterChain {

    @Autowired
    private List<CustomFilter> customFilterList;

    @Autowired
    private ValidateService validateService;

    private int pos = 0;

    @PostConstruct
    public void init() {
        AnnotationAwareOrderComparator.sort(customFilterList);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {
        internalDoFilter(request,response);
    }

    private void internalDoFilter(ServletRequest request, ServletResponse response) {
        if (pos < customFilterList.size()) {
            CustomFilter customFilter = customFilterList.get(pos++);
            customFilter.doFilter(request, response, this);
            return;
        }
        validateService.validateParam("过滤器测试成功");
    }
}
