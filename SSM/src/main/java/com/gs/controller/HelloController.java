package com.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello") // 类似于struts里的命名空间
public class HelloController {

    @RequestMapping("hi")
    public String hello() {
        return "hello"; // 视图的名称
    }

}
