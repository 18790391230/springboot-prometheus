package com.wuyiming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {

    private Random r = new Random();

    @GetMapping("hello")
    public String hello() throws InterruptedException {
        Thread.sleep(r.nextInt(5) * 1000);
        return "hello-prometheus!";
    }
}
