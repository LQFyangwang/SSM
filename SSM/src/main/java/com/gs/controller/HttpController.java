package com.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/javaee")
public class HttpController {

    @GetMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // 接下来直接使用request, response, session
        return "javaee";
    }

}
