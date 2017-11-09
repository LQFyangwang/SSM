package com.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Master on 2017/10/31.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
    }

}
