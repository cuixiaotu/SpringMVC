package com.xiaotu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
