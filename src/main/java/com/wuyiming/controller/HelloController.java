package com.wuyiming.controller;

import com.wuyiming.custom.CustomFilterChain;
import com.wuyiming.req.Abc;
import com.wuyiming.service.ValidateService;
import com.wuyiming.validate.ICreate;
import com.wuyiming.validate.ICreate2;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import javax.websocket.server.PathParam;
import java.util.Random;

@Validated
@RestController
public class HelloController {

    private Random r = new Random();

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ValidateService validateService;

    @Timed(value = "cost_time_test")
    @GetMapping("hello")
    public String hello() throws InterruptedException {
        Thread.sleep(r.nextInt(5) * 1000);
        return "hello-prometheus!";
    }

    @PostMapping("aaa")
    public String aaa(@RequestBody @Validated(ICreate.class) Abc abc) {
        return "aaa success!";
    }

    @Valid
    @GetMapping("bbb")
    public Abc bbb() {
        Abc abc = new Abc();
        return abc;
    }

    @NotEmpty
    @GetMapping("ccc")
    public String ccc() {
        return "";
    }

    @GetMapping("ddd")
    public String ddd() {
        return validateService.validateParam(null);
    }

    @GetMapping("/eee/{abc}/aaa")
    public String eee(@PathVariable String abc) {
        return "eee success!";
    }

    @GetMapping("fff")
    public String fff(ServletRequest request, ServletResponse response) {
        CustomFilterChain bean = beanFactory.getBean(CustomFilterChain.class);
        bean.doFilter(request, response);
        return "fff";
    }
}
