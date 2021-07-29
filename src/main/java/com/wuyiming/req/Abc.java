package com.wuyiming.req;

import com.wuyiming.validate.ICreate;
import com.wuyiming.validate.ICreate2;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Abc {

    @NotEmpty(message = "name不能为空")
    private String name;

    @Valid
    @Size(min = 1, message = "list.size()必须大于{min1}")
    @NotNull(message = "list不能为空", groups = {
            ICreate.class,
            ICreate2.class
    })
    @Size(min = 1, message = "list.size()必须大于{min1}")
    private List<BBB> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BBB> getList() {
        return list;
    }

    public void setList(List<BBB> list) {
        this.list = list;
    }

    public static class BBB{
        @NotNull(message = "age不能为空")
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
