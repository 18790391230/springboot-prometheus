package com.wuyiming.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Validated(Default.class)
public interface ValidateService {

    String validateParam(@NotNull(message = "name不能为空...") String name);
}
