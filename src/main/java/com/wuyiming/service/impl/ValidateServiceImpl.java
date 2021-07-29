package com.wuyiming.service.impl;

import com.wuyiming.service.ValidateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public String validateParam(String name) {
        System.out.println(name);
        System.out.println("\n\n");
        return "validateParam success!";
    }
}
